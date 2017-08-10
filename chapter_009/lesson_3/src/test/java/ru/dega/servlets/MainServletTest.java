package ru.dega.servlets;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.StringWriter;
import java.io.PrintWriter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.atLeast;

/**
 * MainServletTest class.
 *
 * @author Denis
 * @since 10.08.2017
 */
public class MainServletTest {
    /**
     * Http servlet request.
     */
    private HttpServletRequest request;

    /**
     * Http servlet response.
     */
    private HttpServletResponse response;

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
        MainServlet mainServlet = new MainServlet();
        StringWriter sw = new StringWriter();
        Mockito.when(response.getWriter()).thenReturn(new PrintWriter(sw));
        Mockito.when(request.getContextPath()).thenReturn("http://localhost:8080/html/");
        mainServlet.doGet(request, response);
        verify(response, atLeast(1)).getWriter();
        verify(request, atLeast(4)).getContextPath();
    }
}