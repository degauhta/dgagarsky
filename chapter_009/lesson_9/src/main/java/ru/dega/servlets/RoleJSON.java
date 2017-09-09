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
import java.util.List;
import java.util.Map;

/**
 * RoleJSON class.
 *
 * @author Denis
 * @since 31.08.2017
 */
public class RoleJSON extends HttpServlet {
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
        RoleDao roleDao = new RoleSQLDao();
        PrintWriter writer = new PrintWriter(resp.getWriter());
        Map<Integer, String> options = new LinkedHashMap<>();
        List<Role> list = roleDao.getAll();
        for (Role role : list) {
            options.put(role.getId(), role.getName());
        }
        String json = new Gson().toJson(options);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        writer.append(json);
        writer.flush();
    }
}