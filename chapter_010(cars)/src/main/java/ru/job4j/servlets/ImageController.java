package ru.job4j.servlets;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.FileCleanerCleanup;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileCleaningTracker;
import ru.job4j.dao.GenericDAO;
import ru.job4j.models.Image;
import ru.job4j.models.Order;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.List;

/**
 * Image controller. Upload / download images from DB.
 * @author aeremeev.
 * @version 1
 * @since 05.02.2018
 */
public class ImageController extends HttpServlet {

    private GenericDAO dao = new GenericDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("image/jpeg");
        PrintWriter writer = resp.getWriter();
        JsonArray array = new JsonArray();
        int orderID = Integer.valueOf(req.getParameter("order"));
        Order order = (Order) dao.findById(Order.class, orderID);
        for (Image image:order.getImages()) {
            array.add(String.format("data:image/jpeg;base64,%s", DatatypeConverter.printBase64Binary(image.getData())));
        }
        writer.append(array.toString());
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean isMultipartContent = ServletFileUpload.isMultipartContent(req);
        HttpSession session = req.getSession(false);
        Integer userId = (int) session.getAttribute("user_id");
        Integer orderId = (int) session.getAttribute("currentOrder");
        PrintWriter writer = resp.getWriter();
        JsonObject object = new JsonObject();
        object.addProperty("success", false);
        if (isMultipartContent && userId != -1 && orderId != -1) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletContext context = this.getServletConfig().getServletContext();

            FileCleaningTracker tracker = FileCleanerCleanup.getFileCleaningTracker(context);
            factory.setFileCleaningTracker(tracker);
            File repository = (File) context.getAttribute("javax.servlet.context.tempdir");
            factory.setRepository(repository);

            ServletFileUpload upload = new ServletFileUpload(factory);
            try {
                List<FileItem> items = upload.parseRequest(req);
                for (FileItem item: items) {
                    byte[] imageData = item.get();
                    Order order = new Order();
                    order.setId(orderId);
                    Image image = new Image();
                    image.setData(imageData);
                    image.setOrder(order);
                    dao.saveOrUpdate(image);
                }
                object.addProperty("success", true);
            } catch (FileUploadException fue) {
                fue.printStackTrace();
            }
        }
        writer.append(object.toString());
        writer.flush();
    }
}
