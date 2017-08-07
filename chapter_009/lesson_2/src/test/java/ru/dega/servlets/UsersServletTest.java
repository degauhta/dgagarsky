package ru.dega.servlets;

import com.google.common.base.Joiner;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import ru.dega.DBManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.PrintWriter;
import java.sql.SQLException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;

/**
 * UsersServletTest class.
 *
 * @author Denis
 * @since 05.08.2017
 */
@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UsersServletTest {
    /**
     * Http servlet request.
     */
    private HttpServletRequest request;

    /**
     * Http servlet response.
     */
    private HttpServletResponse response;

    /**
     * Datasource mock.
     */
    @Mock
    private DataSource ds;

    /**
     * Database manager mock.
     */
    @Mock
    private DBManager dbManager;

    /**
     * Users servlet.
     */
    @InjectMocks
    private UsersServlet usersServletInject = new UsersServlet();

    /**
     * Initialization.
     */
    @Before
    public void init() {
        this.request = mock(HttpServletRequest.class);
        this.response = mock(HttpServletResponse.class);
    }

    /**
     * Test post.
     *
     * @throws IOException      error
     * @throws ServletException error
     * @throws SQLException     error
     */
    @Test
    public void when1DoPostThenReturnExpectedText() throws IOException, ServletException, SQLException {
        String expectedText = Joiner.on(System.lineSeparator())
                .join("name=name", "login=login", "email=email");
        BufferedReader reader = new BufferedReader(new StringReader(expectedText));
        StringWriter sw = new StringWriter();

        Mockito.when(this.request.getReader()).thenReturn(reader);
        Mockito.when(this.response.getWriter()).thenReturn(new PrintWriter(sw));
        Mockito.when(ds.getConnection()).thenReturn(null);
        Mockito.when(dbManager.addEntry(any(), any())).thenReturn(true);

        this.usersServletInject.doPost(this.request, this.response);
        assertThat(sw.toString(), is("create user name"));
    }

    /**
     * Test get.
     *
     * @throws IOException      error
     * @throws ServletException error
     * @throws SQLException     error
     */
    @Test
    public void when2DoGetThenReturnExpectedText() throws IOException, ServletException, SQLException {
        String name = "name";
        String login = "login";
        String email = "email";
        StringWriter sw = new StringWriter();

        Mockito.when(this.response.getWriter()).thenReturn(new PrintWriter(sw));
        Mockito.when(this.request.getParameter("name")).thenReturn(name);
        Mockito.when(this.request.getParameter("login")).thenReturn(login);
        Mockito.when(this.request.getParameter("email")).thenReturn(email);
        Mockito.when(ds.getConnection()).thenReturn(null);
        Mockito.when(dbManager.findEntry(any(), any(), any(), any())).thenReturn(true);

        this.usersServletInject.doGet(this.request, this.response);
        assertThat(sw.toString(), is("user found"));
    }

    /**
     * Test get.
     *
     * @throws IOException      error
     * @throws ServletException error
     * @throws SQLException     error
     */
    @Test
    public void when3DoPutThenReturnExpectedText() throws IOException, ServletException, SQLException {
        String expectedText = Joiner.on(System.lineSeparator())
                .join("name=name", "login=login", "email=email",
                        "newName=newName", "newLogin=newLogin", "newEmail=newEmail");
        BufferedReader reader = new BufferedReader(new StringReader(expectedText));
        StringWriter sw = new StringWriter();

        Mockito.when(this.request.getReader()).thenReturn(reader);
        Mockito.when(this.response.getWriter()).thenReturn(new PrintWriter(sw));
        Mockito.when(ds.getConnection()).thenReturn(null);
        Mockito.when(dbManager.editEntry(any(), any())).thenReturn(true);

        this.usersServletInject.doPut(this.request, this.response);
        assertThat(sw.toString(), is("update user name"));
    }

    /**
     * Test get.
     *
     * @throws IOException      error
     * @throws ServletException error
     * @throws SQLException     error
     */
    @Test
    public void when4DoDeleteThenReturnExpectedText() throws IOException, ServletException, SQLException {
        String expectedText = Joiner.on(System.lineSeparator())
                .join("name=newName", "login=newLogin",
                        "email=newEmail");
        BufferedReader reader = new BufferedReader(new StringReader(expectedText));
        StringWriter sw = new StringWriter();

        Mockito.when(this.request.getReader()).thenReturn(reader);
        Mockito.when(this.response.getWriter()).thenReturn(new PrintWriter(sw));
        Mockito.when(ds.getConnection()).thenReturn(null);
        Mockito.when(dbManager.deleteEntry(any(), any())).thenReturn(true);

        this.usersServletInject.doDelete(this.request, this.response);
        assertThat(sw.toString(), is("delete user newName"));
    }
}