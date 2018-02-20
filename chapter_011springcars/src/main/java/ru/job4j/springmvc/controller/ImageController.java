package ru.job4j.springmvc.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.FileCleanerCleanup;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileCleaningTracker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ru.job4j.springmvc.dao.GenericDAO;
import ru.job4j.springmvc.models.Image;
import ru.job4j.springmvc.models.Order;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.util.List;

@RestController
public class ImageController {

    private GenericDAO dao = new GenericDAO();

    @GetMapping(value = "/image", produces = "application/json;charset=UTF-8")
    public String getOrderImages(@RequestParam String order) {
        JsonArray array = new JsonArray();
        int orderID = Integer.valueOf(order);
        Order requestedOrder = (Order) dao.findById(Order.class, orderID);
        for (Image image:requestedOrder.getImages()) {
            array.add(String.format("data:image/jpeg;base64,%s", DatatypeConverter.printBase64Binary(image.getData())));
        }
        return array.toString();
    }

    @PostMapping(value = "/image", produces = "application/json;charset=UTF-8")
    public String uploadOrderImages(HttpServletRequest req, HttpSession session) {
        boolean isMultipartContent = ServletFileUpload.isMultipartContent(req);
        Integer userId = (int) session.getAttribute("user_id");
        Integer orderId = (int) session.getAttribute("currentOrder");
        JsonObject object = new JsonObject();
        object.addProperty("success", false);
        if (isMultipartContent && userId != -1 && orderId != -1) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletContext context = ((ServletRequestAttributes) RequestContextHolder.
                    getRequestAttributes()).getRequest().getServletContext();
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
        return object.toString();
    }

}
