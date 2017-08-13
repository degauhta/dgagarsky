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

import java.sql.SQLException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * EditUserTest class.
 *
 * @author Denis
 * @since 10.08.2017
 */
@RunWith(MockitoJUnitRunner.class)
public class EditUserTest {
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
    private EditUser editUserServlet = new EditUser();

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
        editUserServlet.doGet(request, response);
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
        StringWriter sw = new StringWriter();

        Mockito.when(response.getWriter()).thenReturn(new PrintWriter(sw));
        Mockito.when(request.getParameter("name")).thenReturn("test-name");
        Mockito.when(request.getParameter("login")).thenReturn("test-login");
        Mockito.when(request.getParameter("email")).thenReturn("test-email");
        Mockito.when(ds.getConnection()).thenReturn(null);
        Mockito.when(dbManager.editEntry(any(), any(), any(), any())).thenReturn(true);

        editUserServlet.doPost(request, response);
        assertThat(sw.toString(), is("edit user test-login"));
    }
}