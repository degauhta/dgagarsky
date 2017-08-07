package ru.dega.servlets;

import ru.dega.DBManager;
import ru.dega.models.User;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * UsersServlet class.
 *
 * @author Denis
 * @since 03.08.2017
 */
public class UsersServlet extends HttpServlet {
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
        setDBManager(getClass().getClassLoader());
        try {
            InitialContext initContext = new InitialContext();
            this.ds = (DataSource) initContext.lookup("java:comp/env/jdbc/users");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Set db manager.
     *
     * @param classLoader class loader
     */
    private void setDBManager(ClassLoader classLoader) {
        this.dbManager = new DBManager();
        try {
            dbManager.start(classLoader);
        } catch (IOException e) {
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
        Map<String, String> userData = getLoginDataFromRequestBody(req);
        PrintWriter printWriter = new PrintWriter(resp.getWriter());
        if (userData.size() == 3) {
            if (this.dbManager.addEntry(getConnection(), new User(userData.get("name"),
                    userData.get("login"), userData.get("email"), LocalDateTime.now()))) {
                printWriter.append(String.format("create user %s", userData.get("name")));
            }
        } else {
            printWriter.append("not enough data");
        }
        printWriter.flush();
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
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        PrintWriter printWriter = new PrintWriter(resp.getWriter());
        if (this.dbManager.findEntry(getConnection(), name, login, email)) {
            printWriter.append("user found");
        } else {
            printWriter.append("user not found");
        }
        printWriter.flush();
    }

    /**
     * Allow a servlet to handle a PUT request.
     * The PUT operation allows a client to place a file on the server and is similar to sending a file by FTP.
     *
     * @param req  request
     * @param resp response
     * @throws ServletException error
     * @throws IOException      error
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Map<String, String> userData = getLoginDataFromRequestBody(req);
        PrintWriter printWriter = new PrintWriter(resp.getWriter());
        if (userData.size() == 6) {
            this.dbManager.editEntry(getConnection(), userData);
            printWriter.append(String.format("update user %s", userData.get("name")));
        } else {
            printWriter.append("not enough data");
        }
        printWriter.flush();
    }

    /**
     * Allow a servlet to handle a DELETE request.
     * The DELETE operation allows a client to remove a document or Web page from the server.
     *
     * @param req  request
     * @param resp response
     * @throws ServletException error
     * @throws IOException      error
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Map<String, String> userData = getLoginDataFromRequestBody(req);
        PrintWriter printWriter = new PrintWriter(resp.getWriter());
        if (userData.size() == 3) {
            this.dbManager.deleteEntry(getConnection(), userData);
            printWriter.append(String.format("delete user %s", userData.get("name")));
        } else {
            printWriter.append("not enough data");
        }
        printWriter.flush();
    }

    /**
     * Parse body.
     *
     * @param req HttpServletRequest
     * @return map contains 'name', 'login' and 'email'
     */
    private Map<String, String> getLoginDataFromRequestBody(HttpServletRequest req) {
        Map<String, String> result = new HashMap<>();
        String line;
        try {
            BufferedReader reader = req.getReader();
            while ((line = reader.readLine()) != null) {
                if (line.substring(0, 5).equals("name=")) {
                    result.put("name", line.substring(5));
                } else if (line.substring(0, 6).equals("login=")) {
                    result.put("login", line.substring(6));
                } else if (line.substring(0, 6).equals("email=")) {
                    result.put("email", line.substring(6));
                } else if (line.substring(0, 8).equals("newName=")) {
                    result.put("newName", line.substring(8));
                } else if (line.substring(0, 9).equals("newLogin=")) {
                    result.put("newLogin", line.substring(9));
                } else if (line.substring(0, 9).equals("newEmail=")) {
                    result.put("newEmail", line.substring(9));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}