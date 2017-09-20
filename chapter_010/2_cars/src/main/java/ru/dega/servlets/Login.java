package ru.dega.servlets;

import ru.dega.dao.UserDao;
import ru.dega.dao.hibernate.UserHibernateDao;
import ru.dega.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

/**
 * Login class.
 *
 * @author Denis
 * @since 16.09.2017
 */
public class Login extends HttpServlet {
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
        req.getRequestDispatcher("./login.html").forward(req, resp);
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
        PrintWriter writer = new PrintWriter(resp.getWriter());
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        UserDao userDao = new UserHibernateDao();
        User user = userDao.getUserByLogin(login);
        if (user != null && user.getPassword().equals(password)) {
            HttpSession session = req.getSession();
            session.setAttribute("login", login);
            writer.append(String.format("%s/", req.getContextPath()));
            resp.addCookie(new Cookie("login",
                    URLEncoder.encode(login, "UTF-8")));
        } else {
            writer.append("error. user/login not credential");
        }
        writer.flush();
    }
}