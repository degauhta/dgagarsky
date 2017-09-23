package ru.dega.servlets.json;

import com.google.gson.Gson;
import ru.dega.dao.AdvertisementDao;
import ru.dega.dao.hibernate.AdvertisementHibernateDao;
import ru.dega.models.Advertisement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.List;

/**
 * AdvertisementJSON class.
 *
 * @author Denis
 * @since 16.09.2017
 */
public class AdvertisementJSON extends HttpServlet {
    /**
     * Handle a POST request.
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     * @throws ServletException error
     * @throws IOException      error
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AdvertisementDao advertisementDao = new AdvertisementHibernateDao();
        resp.setContentType("text/json");
        PrintWriter writer = new PrintWriter(resp.getWriter());
        String[] filters = new Gson()
                .fromJson(req.getParameter("filters"), String[].class);
        List<Advertisement> list;
        if (filters != null) {
            list = advertisementDao.getFilteredAdvertisement(filters);
        } else if (req.getParameter("showAll").equals("true")) {
            list = advertisementDao.getAllAdvertisement();
        } else {
            list = advertisementDao.getAllNotSoldAdvertisement();
        }
        for (Advertisement ad : list) {
            byte[] photo = ad.getPhoto();
            if (photo != null) {
                ad.setPhotoStr(Base64.getEncoder().encodeToString(photo));
            }
        }
        writer.append(new Gson().toJson(list));
        writer.flush();
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
        AdvertisementDao advertisementDao = new AdvertisementHibernateDao();
        PrintWriter writer = new PrintWriter(resp.getWriter());
        int updateId = Integer.valueOf(req.getParameter("updateId"));
        boolean result = advertisementDao.updateSoldStatus(updateId);
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        writer.append(String.valueOf(result));
        writer.flush();
    }
}