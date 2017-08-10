package ru.dega.servlets;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import ru.dega.DBManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import java.io.IOException;
import java.io.StringWriter;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.StringReader;

import java.sql.SQLException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * DeleteUserTest class.
 *
 * @author Denis
 * @since 10.08.2017
 */
@RunWith(MockitoJUnitRunner.class)
public class DeleteUserTest {
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
    private DeleteUser deleteUserServlet = new DeleteUser();

    /**
     * Initialization.
     */
    @Before
    public void init() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
    }

    /**
     * Test.
     *
     * @throws IOException      error
     * @throws ServletException error
     */
    @Test
    public void testGet() throws IOException, ServletException {
        StringWriter sw = new StringWriter();
        Mockito.when(response.getWriter()).thenReturn(new PrintWriter(sw));
        Mockito.when(request.getContextPath()).thenReturn("http://localhost:8080/html/");
        deleteUserServlet.doGet(request, response);
        verify(response, atLeast(1)).getWriter();
        verify(request, atLeast(1)).getContextPath();
    }

    /**
     * Test post.
     *
     * @throws ServletException error
     * @throws IOException      error
     * @throws SQLException     error
     */
    @Test
    public void testPost() throws ServletException, IOException, SQLException {
        String expectedText = "login=login";
        BufferedReader reader = new BufferedReader(new StringReader(expectedText));
        StringWriter sw = new StringWriter();

        Mockito.when(request.getReader()).thenReturn(reader);
        Mockito.when(request.getServletPath()).thenReturn("/delete");
        Mockito.when(response.getWriter()).thenReturn(new PrintWriter(sw));
        Mockito.when(ds.getConnection()).thenReturn(null);
        Mockito.when(dbManager.deleteEntry(any(), any())).thenReturn(true);

        deleteUserServlet.doPost(request, response);
        assertThat(sw.toString(), is("delete user login"));
    }
}