package ru.dega.servlets;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.dega.dao.AdvertisementDao;
import ru.dega.dao.UserDao;
import ru.dega.dao.hibernate.AdvertisementHibernateDao;
import ru.dega.dao.hibernate.UserHibernateDao;
import ru.dega.models.Advertisement;
import ru.dega.models.CarBody;
import ru.dega.models.CarBrand;
import ru.dega.models.CarDriveType;
import ru.dega.models.CarEngine;
import ru.dega.models.CarModel;
import ru.dega.models.CarTransmission;
import ru.dega.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * NewAdvert class.
 *
 * @author Denis
 * @since 17.09.2017
 */
public class NewAdvert extends HttpServlet {
    /**
     * Handle a GET request.
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     * @throws ServletException error
     * @throws IOException      error
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("./newadvert.html").forward(req, resp);
    }

    /**
     * Allow a servlet to handle a POST request.
     * The HTTP POST method allows the client to send data
     * of unlimited length to the Web server a single time
     * and is useful when posting information such as credit card numbers.
     *
     * @param req  request
     * @param resp response
     * @throws ServletException error
     * @throws IOException      error
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        AdvertisementDao advertisementDao = new AdvertisementHibernateDao();
        Map<String, String> formData = new HashMap<>();
        byte[] photo = null;
        if (ServletFileUpload.isMultipartContent(req)) {
            try {
                List<FileItem> multiparts = new ServletFileUpload(
                        new DiskFileItemFactory()).parseRequest(req);
                for (FileItem item : multiparts) {
                    if (!item.isFormField()) {
                        photo = item.get();
                    } else {
                        formData.put(item.getFieldName(), item.getString());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        UserDao userDao = new UserHibernateDao();
        String login = getLogin(req);
        int userId = -1;
        if (login != null) {
            userId = userDao.getUserByLogin(login).getId();
        }
        Advertisement advert = new Advertisement();
        advert.setDescription(formData.get("description"));
        advert.setSold(false);
        advert.setPhoto(photo);
        advert.setAuthor(new User(userId, null, null));
        advert.setCarBrand(new CarBrand(Integer.valueOf(formData.get("carBrand")), null));
        advert.setCarModel(new CarModel(Integer.valueOf(formData.get("carModel")), null));
        advert.setCarBody(new CarBody(Integer.valueOf(formData.get("carBody")), null));
        advert.setCarTransmission(new CarTransmission(Integer.valueOf(formData.get("carTransmission")), null));
        advert.setCarEngine(new CarEngine(Integer.valueOf(formData.get("carEngine")), null));
        advert.setCarDriveType(new CarDriveType(Integer.valueOf(formData.get("carDriveType")), null));
        advertisementDao.createAdvertisement(advert);

        req.getRequestDispatcher("index.html").forward(req, resp);
    }

    /**
     * Get login from cookies.
     *
     * @param req HTTP request
     * @return login
     */
    private String getLogin(HttpServletRequest req) {
        String login = null;
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("login")) {
                    login = cookie.getValue();
                }
            }
        }
        return login;
    }
}