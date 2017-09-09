package ru.dega.servlets;

import com.google.gson.Gson;
import ru.dega.dao.MusicTypeDAO;
import ru.dega.dao.sql.MusicTypeSQLDao;
import ru.dega.models.MusicType;

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
 * MusicTypeJSON class.
 *
 * @author Denis
 * @since 31.08.2017
 */
public class MusicTypeJSON extends HttpServlet {
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
        MusicTypeDAO musicTypeDAO = new MusicTypeSQLDao();
        PrintWriter writer = new PrintWriter(resp.getWriter());
        Map<Integer, String> options = new LinkedHashMap<>();
        List<MusicType> list = musicTypeDAO.getAll();
        for (MusicType musicType : list) {
            options.put(musicType.getId(), musicType.getName());
        }
        String json = new Gson().toJson(options);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        writer.append(json);
        writer.flush();
    }
}