package ru.dega.servlets;

import com.google.gson.Gson;
import ru.dega.dao.UserDao;
import ru.dega.dao.sql.AddressSQLDao;
import ru.dega.dao.sql.UserSQLDao;
import ru.dega.models.Address;
import ru.dega.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * CreateUser class.
 *
 * @author Denis
 * @since 31.08.2017
 */
public class UserCreate extends HttpServlet {
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
        req.getRequestDispatcher("./user.html").forward(req, resp);
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
        int id = userDao.createUser(new User(0,
                req.getParameter("login"),
                Integer.valueOf(req.getParameter("roleId"))),
                musicID);
        if (id != -1) {
            options.put("error", "");
            options.put("href", String.format("%s/", req.getContextPath()));
            new AddressSQLDao().create(new Address(0, req.getParameter("address"), id));
        } else {
            options.put("error", "login in use");
            options.put("href", String.format("%s/user.html", req.getContextPath()));
        }
        String json = new Gson().toJson(options);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        writer.append(json);
        writer.flush();
    }
}