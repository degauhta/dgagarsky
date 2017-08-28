package ru.dega.servlets;

import ru.dega.DBManager;
import ru.dega.models.User;
import ru.dega.models.UserRole;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * EditUser class.
 *
 * @author Denis
 * @since 11.08.2017
 */
public class EditUser extends HttpServlet {
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
        String editedLogin = req.getParameter("editedLogin");
        if (editedLogin != null) {
            User user = DBManager.getInstance().getEntry(editedLogin);
            resp.addCookie(new Cookie("editedLogin", editedLogin));
            resp.addCookie(new Cookie("password", user.getPassword()));
            resp.addCookie(new Cookie("name", user.getName()));
            resp.addCookie(new Cookie("email", user.getEmail()));
            resp.addCookie(new Cookie("country", user.getCountry()));
            resp.addCookie(new Cookie("town", user.getTown()));
        }
        resp.sendRedirect(String.format("%s/edituser.html", req.getContextPath()));
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
        String editedLogin = req.getParameter("editedLogin");
        String error = "";
        if (!editedLogin.equals("root")) {
            HttpSession session = req.getSession();
            if (session.getAttribute("role") == UserRole.ADMINISTRATOR
                    || session.getAttribute("login").equals(editedLogin)) {
                DBManager.getInstance().editEntry(editedLogin,
                        req.getParameter("name"), req.getParameter("email"),
                        req.getParameter("country"), req.getParameter("town"));
            } else {
                error = "Error. User can edit only themselves!";
            }
        } else {
            error = "Error. Cant edit user root!";
        }
        if (error.length() > 0) {
            writer.append(error);
        } else {
            writer.append(String.format("%s/", req.getContextPath()));
        }
        writer.flush();
    }
}