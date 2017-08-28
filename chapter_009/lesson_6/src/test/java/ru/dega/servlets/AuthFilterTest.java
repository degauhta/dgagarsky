package ru.dega.servlets;

import org.junit.Test;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * AuthFilterTest class.
 *
 * @author Denis
 * @since 28.08.2017
 */
public class AuthFilterTest {
    /**
     * Test Filter.
     *
     * @throws ServletException error
     * @throws IOException      error
     */
    @Test
    public void whenURIContainsRightPhrase() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain chain = mock(FilterChain.class);
        AuthFilter authFilter = new AuthFilter();

        when(request.getRequestURI()).thenReturn("/signin");

        authFilter.doFilter(request, response, chain);
        verify(chain, atLeastOnce()).doFilter(request, response);
    }

    /**
     * Test Filter.
     *
     * @throws ServletException error
     * @throws IOException      error
     */
    @Test
    public void whenURINotContainsRightPhraseAndLoginIsNull() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain chain = mock(FilterChain.class);
        HttpSession session = mock(HttpSession.class);
        AuthFilter authFilter = new AuthFilter();

        when(request.getRequestURI()).thenReturn("qwerty");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("login")).thenReturn(null);

        authFilter.doFilter(request, response, chain);
        verify(response, atLeastOnce()).sendRedirect(any());
    }

    /**
     * Test Filter.
     *
     * @throws ServletException error
     * @throws IOException      error
     */
    @Test
    public void whenURINotContainsRightPhraseAndLoginNotNull() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain chain = mock(FilterChain.class);
        HttpSession session = mock(HttpSession.class);
        AuthFilter authFilter = new AuthFilter();

        when(request.getRequestURI()).thenReturn("qwerty");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("login")).thenReturn("qwerty");

        authFilter.doFilter(request, response, chain);
        verify(chain, atLeastOnce()).doFilter(request, response);
    }
}