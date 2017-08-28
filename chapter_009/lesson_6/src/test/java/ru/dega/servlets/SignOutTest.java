package ru.dega.servlets;

import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * SignOutTest class.
 *
 * @author Denis
 * @since 28.08.2017
 */
public class SignOutTest {
    /**
     * Test.
     *
     * @throws ServletException error
     * @throws IOException      error
     */
    @Test
    public void testGet() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        SignOut signOut = new SignOut();

        when(req.getSession()).thenReturn(session);
        when(req.getContextPath()).thenReturn("test");

        signOut.doGet(req, resp);
        verify(resp, atLeastOnce()).sendRedirect("test/");
    }
}