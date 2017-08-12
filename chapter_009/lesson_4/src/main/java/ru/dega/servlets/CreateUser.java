package ru.dega.servlets;

import ru.dega.DBManager;
import ru.dega.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * CreateUser class.
 *
 * @author Denis
 * @since 11.08.2017
 */
public class CreateUser extends HttpServlet {
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
        resp.setContentType("text/html");
        DBManager.getInstance().addEntry(new User(req.getParameter("name"),
                req.getParameter("login"), req.getParameter("email"),
                LocalDateTime.now()));
        resp.sendRedirect(String.format("%s/index.jsp", req.getContextPath()));
    }
}