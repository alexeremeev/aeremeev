package ru.job4j.web;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.FileCleanerCleanup;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileCleaningTracker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ru.job4j.domain.Image;
import ru.job4j.domain.Order;
import ru.job4j.repository.ImageRepository;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.util.List;

@RestController
public class ImageController {

    @Autowired
    private ImageRepository imagesRepo;

    @GetMapping(value = "/image", produces = "application/json;charset=UTF-8")
    public String getOrderImages(@RequestParam String order) {
        JsonArray array = new JsonArray();
        int orderID = Integer.valueOf(order);
        List<Image> images = imagesRepo.findByOrderId(orderID);
        for (Image image:images) {
            array.add(String.format("data:image/jpeg;base64,%s", DatatypeConverter.printBase64Binary(image.getData())));
        }
        return array.toString();
    }

    @PostMapping(value = "/image", produces = "application/json;charset=UTF-8")
    public String uploadOrderImages(HttpServletRequest req, HttpSession session) {
        boolean isMultipartContent = ServletFileUpload.isMultipartContent(req);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String login = ((UserDetails) authentication.getPrincipal()).getUsername();
        Integer orderId = (int) session.getAttribute("currentOrder");
        JsonObject object = new JsonObject();
        object.addProperty("success", false);
        if (isMultipartContent && login != null && orderId != -1) {
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
                    imagesRepo.save(image);
                }
                object.addProperty("success", true);
            } catch (FileUploadException fue) {
                fue.printStackTrace();
            }
        }
        return object.toString();
    }

}
