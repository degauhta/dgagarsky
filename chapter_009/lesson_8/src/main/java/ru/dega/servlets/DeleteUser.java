package ru.dega.servlets;

import com.google.gson.Gson;
import ru.dega.DBManager;
import ru.dega.models.UserRole;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

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
        PrintWriter writer = new PrintWriter(resp.getWriter());
        Map<String, String> options = new LinkedHashMap<>();
        String deletedLogin = req.getParameter("login");
        String error = "";
        String href = "";
        if (!deletedLogin.equals("root")) {
            HttpSession session = req.getSession();
            boolean sameUser = session.getAttribute("login").equals(deletedLogin);
            if (session.getAttribute("role") == UserRole.ADMINISTRATOR
                    || sameUser) {
                DBManager.getInstance().deleteEntry(req.getParameter("login"));
                if (sameUser) {
                    href = String.format("%s/signout", req.getContextPath());
                }
            } else {
                error = "User can delete only themselves!";
            }
        } else {
            error = "Can`t delete user root!";
        }

        options.put("href", href);
        options.put("error", error);
        String json = new Gson().toJson(options);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        writer.append(json);
        writer.flush();
    }
}