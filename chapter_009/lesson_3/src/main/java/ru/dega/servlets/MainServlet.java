package ru.dega.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * MainServlet class.
 *
 * @author Denis
 * @since 08.08.2017
 */
public class MainServlet extends HttpServlet {
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
        resp.setContentType("text/html");
        PrintWriter printWriter = new PrintWriter(resp.getWriter());
        printWriter.append("<!DOCTYPE html>"
                + "<html lang=\"en\">"
                + "<head>"
                + "    <meta charset=\"UTF-8\">"
                + "    <title></title>"
                + "</head>"
                + "<body>"
                + "<form action='" + req.getContextPath() + "/get' method='get'>"
                + "<input type='submit' value='get all users'><br>"
                + "</form>"
                + "<br>"
                + "<form action='" + req.getContextPath() + "/create' method='get'>"
                + "<input type='submit' value='create user'>"
                + "</form>"
                + "<br>"
                + "<form action='" + req.getContextPath() + "/edit' method='get'>"
                + "<input type='submit' value='edit user'>"
                + "</form>"
                + "<br>"
                + "<form action='" + req.getContextPath() + "/delete' method='get'>"
                + "<input type='submit' value='delete user'>"
                + "</form>"
                + "</body>");
        printWriter.flush();
    }
}