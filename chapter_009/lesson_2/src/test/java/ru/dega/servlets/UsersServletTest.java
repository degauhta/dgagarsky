package ru.dega.servlets;

import com.google.common.base.Joiner;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.Before;
import org.junit.AfterClass;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import ru.dega.DBManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

/**
 * UsersServletTest class.
 *
 * @author Denis
 * @since 05.08.2017
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SuppressWarnings("ALL")
public class UsersServletTest {
    /**
     * Users servlet instance.
     */
    private UsersServlet usersServlet;

    /**
     * Http servlet request.
     */
    private HttpServletRequest request;

    /**
     * Http servlet response.
     */
    private HttpServletResponse response;

    /**
     * Properties.
     */
    private static Properties prop;

    /**
     * Initialization.
     */
    @Before
    public void init() {
        this.usersServlet = new UsersServlet();
        this.request = mock(HttpServletRequest.class);
        this.response = mock(HttpServletResponse.class);
        prop = new Properties();
        ClassLoader classLoader = DBManager.class.getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream("app.properties")) {
            prop.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Drop database.
     *
     * @throws SQLException error
     */
    @AfterClass
    public static void dropDatabase() throws SQLException {
        DBManager dbManager = new DBManager();
        Connection connection = dbManager.connectToServer(prop.getProperty("dbUrl"),
                prop.getProperty("dbUser"), prop.getProperty("dbPassword"));
        String dropConnections = "SELECT pg_terminate_backend(pg_stat_activity.pid) "
                + "FROM pg_stat_activity WHERE pg_stat_activity.datname = 'dega_test_servlet_user' "
                + "AND pid <> pg_backend_pid();";
        Statement statement = connection.createStatement();
        statement.executeQuery(dropConnections);
        String sql = String.format("DROP DATABASE IF EXISTS %s;", prop.getProperty("dbName"));
        statement = connection.createStatement();
        statement.executeUpdate(sql);
    }

    /**
     * Test post.
     *
     * @throws IOException      error
     * @throws ServletException error
     */
    @Test
    public void when1DoPostThenReturnExpectedText() throws IOException, ServletException {
        String expectedText = Joiner.on(System.lineSeparator())
                .join("name=name", "login=login", "email=email");
        BufferedReader reader = new BufferedReader(new StringReader(expectedText));
        StringWriter sw = new StringWriter();

        Mockito.when(this.request.getReader()).thenReturn(reader);
        Mockito.when(this.response.getWriter()).thenReturn(new PrintWriter(sw));

        this.usersServlet.setDBManager(getClass().getClassLoader());
        this.usersServlet.doPost(this.request, this.response);
        assertThat(sw.toString(), is("create user name"));
    }

    /**
     * Test get.
     *
     * @throws IOException      error
     * @throws ServletException error
     */
    @Test
    public void when2DoGetThenReturnExpectedText() throws IOException, ServletException {
        String name = "name";
        String login = "login";
        String email = "email";
        StringWriter sw = new StringWriter();

        Mockito.when(this.response.getWriter()).thenReturn(new PrintWriter(sw));
        Mockito.when(this.request.getParameter("name")).thenReturn(name);
        Mockito.when(this.request.getParameter("login")).thenReturn(login);
        Mockito.when(this.request.getParameter("email")).thenReturn(email);

        this.usersServlet.setDBManager(getClass().getClassLoader());
        this.usersServlet.doGet(this.request, this.response);
        assertThat(sw.toString(), is("user found"));
    }

    /**
     * Test get.
     *
     * @throws IOException      error
     * @throws ServletException error
     */
    @Test
    public void when3DoPutThenReturnExpectedText() throws IOException, ServletException {
        String expectedText = Joiner.on(System.lineSeparator())
                .join("name=name", "login=login", "email=email",
                        "newName=newName", "newLogin=newLogin", "newEmail=newEmail");
        BufferedReader reader = new BufferedReader(new StringReader(expectedText));
        StringWriter sw = new StringWriter();

        Mockito.when(this.request.getReader()).thenReturn(reader);
        Mockito.when(this.response.getWriter()).thenReturn(new PrintWriter(sw));

        this.usersServlet.setDBManager(getClass().getClassLoader());
        this.usersServlet.doPut(this.request, this.response);
        assertThat(sw.toString(), is("update user name"));
    }

    /**
     * Test get.
     *
     * @throws IOException      error
     * @throws ServletException error
     */
    @Test
    public void when4DoDeleteThenReturnExpectedText() throws IOException, ServletException {
        String expectedText = Joiner.on(System.lineSeparator())
                .join("name=newName", "login=newLogin",
                        "email=newEmail");
        BufferedReader reader = new BufferedReader(new StringReader(expectedText));
        StringWriter sw = new StringWriter();

        Mockito.when(this.request.getReader()).thenReturn(reader);
        Mockito.when(this.response.getWriter()).thenReturn(new PrintWriter(sw));

        this.usersServlet.setDBManager(getClass().getClassLoader());
        this.usersServlet.doDelete(this.request, this.response);
        assertThat(sw.toString(), is("delete user newName"));
    }
}