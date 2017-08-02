package ru.dega.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * EchoServlet class.
 *
 * @author Denis
 * @since 02.08.2017
 */
public class EchoServlet extends HttpServlet {
    /**
     * Handle a GET request.
     * @param req HttpServletRequest
     * @param resp HttpServletResponse
     * @throws ServletException error
     * @throws IOException error
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("Hello world.");
        writer.flush();
    }
}
