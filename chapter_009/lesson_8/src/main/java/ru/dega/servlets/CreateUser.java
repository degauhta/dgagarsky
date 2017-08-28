package ru.dega.servlets;

import com.google.gson.Gson;
import ru.dega.DBManager;
import ru.dega.models.User;
import ru.dega.models.UserRole;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * CreateUser class.
 *
 * @author Denis
 * @since 11.08.2017
 */
public class CreateUser extends HttpServlet {
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
        req.getRequestDispatcher("./createuser.html").forward(req, resp);
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
        Map<String, String> options = new LinkedHashMap<>();
        PrintWriter writer = new PrintWriter(resp.getWriter());
        boolean result = false;
        String error = "Not unique login!";
        HttpSession session = req.getSession();
        if (session.getAttribute("role") == UserRole.ADMINISTRATOR) {
            result = DBManager.getInstance().addEntry(new User(req.getParameter("login"),
                    req.getParameter("password"),
                    req.getParameter("name"),
                    req.getParameter("email"),
                    LocalDateTime.now(),
                    req.getParameter("country"),
                    req.getParameter("town"),
                    UserRole.valueOf(req.getParameter("role"))));
        } else {
            error = "User cant create new users/administrators";
        }
        if (result) {
            options.put("error", "");
            options.put("href", String.format("%s/", req.getContextPath()));
        } else {
            options.put("error", error);
            options.put("href", String.format("%s/createuser.html", req.getContextPath()));
        }
        String json = new Gson().toJson(options);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        writer.append(json);
        writer.flush();
    }
}