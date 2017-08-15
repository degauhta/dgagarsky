package ru.dega.servlets;

import ru.dega.DBManager;
import ru.dega.models.UserRole;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * DeleteUser class.
 *
 * @author Denis
 * @since 11.08.2017
 */
public class DeleteUser extends HttpServlet {
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
        String deletedLogin = req.getParameter("login");
        String error = "";
        if (!deletedLogin.equals("root")) {
            HttpSession session = req.getSession();
            synchronized (session) {
                boolean sameUser = session.getAttribute("login").equals(deletedLogin);
                if (session.getAttribute("role") == UserRole.ADMINISTRATOR
                        || sameUser) {
                    DBManager.getInstance().deleteEntry(req.getParameter("login"));
                    if (sameUser) {
                        resp.sendRedirect(String.format("%s/signout", req.getContextPath()));
                    } else {
                        resp.sendRedirect(String.format("%s/", req.getContextPath()));
                    }
                } else {
                    error = "User can delete only themselves!";
                }
            }
        } else {
            error = "Cant delete user root!";
        }
        if (error.length() > 0) {
            req.setAttribute("error", error);
            req.setAttribute("users", DBManager.getInstance().getAllEntries());
            req.setAttribute("roles", UserRole.values());
            req.getRequestDispatcher("/WEB-INF/views/UsersView.jsp").forward(req, resp);
        }
    }
}