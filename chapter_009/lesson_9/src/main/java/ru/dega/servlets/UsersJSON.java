package ru.dega.servlets;

import ru.dega.repository.MixedObjectSQLRepository;
import ru.dega.repository.Repository;
import ru.dega.repository.UsersAndBoundAddressRoleMusicTypeSpecification;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * UsersJSON class.
 *
 * @author Denis
 * @since 30.08.2017
 */
public class UsersJSON extends HttpServlet {
    /**
     * Handle a POST request.
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     * @throws ServletException error
     * @throws IOException      error
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Repository<String> repository = new MixedObjectSQLRepository();
        resp.setContentType("text/json");
        PrintWriter writer = new PrintWriter(resp.getWriter());
        List<String> list = repository.query(new UsersAndBoundAddressRoleMusicTypeSpecification());
        writer.append(list.toString());
        writer.flush();
    }
}