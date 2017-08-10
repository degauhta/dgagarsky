package ru.dega.servlets;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import ru.dega.DBManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import java.io.IOException;
import java.io.StringWriter;
import java.io.PrintWriter;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * GetUsersTest class.
 *
 * @author Denis
 * @since 10.08.2017
 */
public class GetUsersTest {
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
    private GetUsers getUserServlet = new GetUsers();

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
        getUserServlet.doGet(request, response);
        verify(response, atLeast(1)).getWriter();
        verify(request, atLeast(1)).getContextPath();
    }
}