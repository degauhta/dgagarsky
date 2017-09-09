package ru.dega.servlets;

import com.google.gson.Gson;
import ru.dega.dao.RoleDao;
import ru.dega.dao.sql.RoleSQLDao;
import ru.dega.models.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Role class.
 *
 * @author Denis
 * @since 01.09.2017
 */
@SuppressWarnings("Duplicates")
public class RoleCRUD extends HttpServlet {
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
        req.getRequestDispatcher("./role.html").forward(req, resp);
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
        Map<String, String> result = new LinkedHashMap<>();
        PrintWriter writer = new PrintWriter(resp.getWriter());
        String operationName = req.getParameter("operationName");
        switch (operationName) {
            case "create":
                create(result, req);
                break;
            case "delete":
                delete(result, req);
                break;
            case "update":
                update(result, req);
                break;
            default:
                break;
        }
        String json = new Gson().toJson(result);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        writer.append(json);
        writer.flush();
    }

    /**
     * Create music type.
     *
     * @param result map with result
     * @param req    http request
     */
    private void create(Map<String, String> result, HttpServletRequest req) {
        RoleDao roleDao = new RoleSQLDao();
        int id = roleDao.create(new ru.dega.models.Role(0, req.getParameter("name")));
        if (id != -1) {
            result.put("error", "");
        } else {
            result.put("error", "name in use");
        }
    }

    /**
     * Delete music type.
     *
     * @param result map with result
     * @param req    http request
     */
    private void delete(Map<String, String> result, HttpServletRequest req) {
        RoleDao roleDao = new RoleSQLDao();
        if (roleDao.deleteByName(req.getParameter("tdName"))) {
            result.put("error", "");
        } else {
            result.put("error", "cant delete role");
        }
    }

    /**
     * Update music type.
     *
     * @param result map with result
     * @param req    http request
     */
    private void update(Map<String, String> result, HttpServletRequest req) {
        RoleDao roleDao = new RoleSQLDao();
        if (roleDao.update(new Role(Integer.valueOf(req.getParameter("tdId")),
                req.getParameter("name"))
        )) {
            result.put("error", "");
        } else {
            result.put("error", "cant update role");
        }
    }
}