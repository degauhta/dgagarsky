package ru.dega.servlets;

import ru.dega.DBManager;
import ru.dega.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * JSONController class.
 *
 * @author Denis
 * @since 20.08.2017
 */
public class JSONController extends HttpServlet {
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
        resp.setContentType("text/json");
        PrintWriter writer = new PrintWriter(resp.getWriter());
        StringBuilder sb = new StringBuilder("[");
        List<User> users = DBManager.getInstance().getAllEntries();
        for (User user : users) {
            if (sb.charAt(sb.toString().length() - 1) == '}') {
                sb.append(", ");
            }
            sb.append("{\"login\":\"");
            sb.append(user.getLogin());
            sb.append("\", \"name\":\"");
            sb.append(user.getName());
            sb.append("\", \"email\":\"");
            sb.append(user.getEmail());
            sb.append("\", \"crdate\":\"");
            sb.append(user.getCreateDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            sb.append("\", \"country\":\"");
            sb.append(user.getCountry());
            sb.append("\", \"town\":\"");
            sb.append(user.getTown());
            sb.append("\", \"role\":\"");
            sb.append(user.getRole().name().toLowerCase());
            sb.append("\"}");
        }
        sb.append("]");
        writer.append(sb.toString());
        writer.flush();
    }
}