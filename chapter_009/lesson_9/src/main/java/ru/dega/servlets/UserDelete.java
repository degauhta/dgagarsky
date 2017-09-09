package ru.dega.servlets;

import ru.dega.dao.UserDao;
import ru.dega.dao.sql.UserSQLDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * DeleteUser class.
 *
 * @author Denis
 * @since 31.08.2017
 */
public class UserDelete extends HttpServlet {
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
        PrintWriter writer = new PrintWriter(resp.getWriter());
        boolean result = userDao
                .deleteByID(Integer.valueOf(req.getParameter("id")));
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        writer.append(String.valueOf(result));
        writer.flush();
    }
}