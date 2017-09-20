package ru.dega.servlets.json;

import com.google.gson.Gson;
import ru.dega.dao.AbstractDao;
import ru.dega.dao.hibernate.AbstractHibernateDao;
import ru.dega.models.CarCategoryAbstract;

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
 * NewAdvertJSON class.
 *
 * @author Denis
 * @since 17.09.2017
 */
public class NewAdvertJSON extends HttpServlet {
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
        PrintWriter writer = new PrintWriter(resp.getWriter());
        Map<Integer, String> result = null;
        String select = req.getParameter("select");
        switch (select) {
            case "car brand":
                result = getCarCategoryData("from CarBrand");
                break;
            case "car model":
                result = getCarCategoryData("from CarModel");
                break;
            case "car body":
                result = getCarCategoryData("from CarBody");
                break;
            case "car transmission":
                result = getCarCategoryData("from CarTransmission");
                break;
            case "car engine":
                result = getCarCategoryData("from CarEngine");
                break;
            case "car drive type":
                result = getCarCategoryData("from CarDriveType");
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
     * Get car category data.
     *
     * @param query sql query
     * @param <T> type of car category
     * @return map of pair id-name
     */
    private <T extends CarCategoryAbstract> Map<Integer, String> getCarCategoryData(String query) {
        Map<Integer, String> result = new LinkedHashMap<>();
        AbstractDao<T> abstractDao = new AbstractHibernateDao<>();
        List<T> list = abstractDao.getAll(query);
        for (T elem : list) {
            result.put(elem.getId(), elem.getName());
        }
        return result;
    }
}