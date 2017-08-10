package ru.dega.servlets;

import ru.dega.DBManager;
import ru.dega.HtmlBodyParse;
import ru.dega.models.User;

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
import java.util.Map;

/**
 * CreateUser class.
 *
 * @author Denis
 * @since 08.08.2017
 */
public class CreateUser extends HttpServlet {
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
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = new PrintWriter(resp.getWriter());
        printWriter.append("<!DOCTYPE html>"
                + "<html lang=\"en\">"
                + "<head>"
                + "    <meta charset=\"UTF-8\">"
                + "    <title></title>"
                + "</head>"
                + "<body>"
                + "<form action='" + req.getContextPath() + "/create' method='post' accept-charset=\"UTF-8\">"
                + "Name: <input type='text' name='name'><br><br>"
                + "Login: <input type='text' name='login'><br><br>"
                + "Email: <input type='text' name='email'><br><br>"
                + "<input type='submit' value='create'>"
                + "</form>"
                + "</body>");
        printWriter.flush();
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
        resp.setContentType("text/html");
        Map<String, String> userData = HtmlBodyParse.getLoginDataFromRequestBody(req);
        PrintWriter printWriter = new PrintWriter(resp.getWriter());
        if (userData.size() == 3) {
            if (this.dbManager.addEntry(getConnection(), new User(userData.get("name"),
                    userData.get("login"), userData.get("email")))) {
                printWriter.append(String.format("create user %s", userData.get("login")));
            }
        } else {
            printWriter.append("not enough data to create user");
        }
        resp.sendRedirect(req.getContextPath());
    }
}