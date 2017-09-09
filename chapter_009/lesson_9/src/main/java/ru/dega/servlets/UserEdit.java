package ru.dega.servlets;

import com.google.gson.Gson;
import ru.dega.dao.UserDao;
import ru.dega.dao.sql.AddressSQLDao;
import ru.dega.dao.sql.UserSQLDao;
import ru.dega.models.Address;
import ru.dega.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * UserUpdate class.
 *
 * @author Denis
 * @since 02.09.2017
 */
public class UserEdit extends HttpServlet {
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
        UserDao userDao = new UserSQLDao();
        String editedName = req.getParameter("editedName");
        clearCookies(req, resp);
        if (editedName != null) {
            User user = userDao.getByName(editedName);
            resp.addCookie(new Cookie("userName",
                    URLEncoder.encode(String.valueOf(editedName), "UTF-8")));
            resp.addCookie(new Cookie("userID", String.valueOf(user.getId())));
            resp.addCookie(new Cookie("roleId", String.valueOf(user.getRoleId())));
            Address address = new AddressSQLDao().getByUserId(user.getId());
            if (address != null) {
                resp.addCookie(new Cookie("addressRepresentation",
                        URLEncoder.encode(address.getRepresentation(), "UTF-8")));
            }
            resp.addCookie(new Cookie("musicId",
                    URLEncoder.encode(req.getParameter("musicId"), "UTF-8")));
        }
        resp.sendRedirect(String.format("%s/useredit.html", req.getContextPath()));
    }

    /**
     * Clear cookies.
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     */
    private void clearCookies(HttpServletRequest req, HttpServletResponse resp) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setValue(null);
                cookie.setMaxAge(0);
                resp.addCookie(cookie);
            }
        }
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
        UserDao userDao = new UserSQLDao();
        Map<String, String> options = new LinkedHashMap<>();
        PrintWriter writer = new PrintWriter(resp.getWriter());
        String[] musicID = req.getParameterValues("musicID[]");
        int userID = Integer.valueOf(req.getParameter("id"));
        String newLogin = req.getParameter("newLogin");
        String login = req.getParameter("login");
        boolean result = userDao.update(new User(userID,
                        newLogin,
                        Integer.valueOf(req.getParameter("roleId"))),
                musicID, login.equals(newLogin));
        if (result) {
            options.put("error", "");
            options.put("href", String.format("%s/", req.getContextPath()));
            new AddressSQLDao().update(new Address(0, req.getParameter("address"), userID));
        } else {
            options.put("error", "login in use");
            options.put("href", "");
        }
        String json = new Gson().toJson(options);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        writer.append(json);
        writer.flush();
    }
}