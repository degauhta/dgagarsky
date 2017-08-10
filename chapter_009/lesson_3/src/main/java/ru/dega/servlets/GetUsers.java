package ru.dega.servlets;

import ru.dega.DBManager;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * GetUsers class.
 *
 * @author Denis
 * @since 09.08.2017
 */
public class GetUsers extends HttpServlet {
    /**
     * Database manager.
     */
    private DBManager dbManager;

    /**
     * Data source.
     */
    private DataSource ds;

    /**
     * Init servlet.
     *
     * @throws ServletException error
     */
    @Override
    public void init() throws ServletException {
        this.dbManager = new DBManager();
        try {
            InitialContext initContext = new InitialContext();
            this.ds = (DataSource) initContext.lookup("java:comp/env/jdbc/html");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get connection from data source.
     *
     * @return connection
     */
    private Connection getConnection() {
        Connection connection = null;
        try {
            connection = ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

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
        List<String> users = this.dbManager.getAllEntries(getConnection());
        PrintWriter printWriter = new PrintWriter(resp.getWriter());
        printWriter.append("<!DOCTYPE html>"
                + "<html lang=\"en\">"
                + "<head>"
                + "    <meta charset=\"UTF-8\">"
                + "    <title></title>"
                + "</head>"
                + "<body>");
        for (String s : users) {
            printWriter.append(String.format("%s<br>", s));
        }
        printWriter.append("<form action='" + req.getContextPath() + "' method='get'>"
                + "<input type='submit' value='ok'>"
                + "</body>");
        printWriter.flush();
    }
}
