package ru.dega.servlets;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.dega.DBManager;

import javax.servlet.RequestDispatcher;
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
 * SignInControllerTest class.
 *
 * @author Denis
 * @since 28.08.2017
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(DBManager.class)
public class SignInControllerTest {
    /**
     * Test servlet.
     *
     * @throws ServletException error
     * @throws IOException      error
     */
    @Test
    public void whenLoginNotCredentialThenSetError() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        SignInController signInController = new SignInController();
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        DBManager dbManager = mock(DBManager.class);

        PowerMockito.mockStatic(DBManager.class);
        BDDMockito.given(DBManager.getInstance()).willReturn(dbManager);
        when(dbManager.isCredential(any(), any())).thenReturn(false);
        when(request.getRequestDispatcher(any())).thenReturn(requestDispatcher);

        signInController.doPost(request, response);
        verify(request, atLeastOnce()).setAttribute("error", "Credential invalid!");
    }

    /**
     * Test servlet.
     *
     * @throws ServletException error
     * @throws IOException      error
     */
    @Test
    public void whenLoginCredentialThenSetError() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        SignInController signInController = new SignInController();
        HttpSession session = mock(HttpSession.class);
        DBManager dbManager = mock(DBManager.class);

        PowerMockito.mockStatic(DBManager.class);
        BDDMockito.given(DBManager.getInstance()).willReturn(dbManager);
        when(dbManager.isCredential(any(), any())).thenReturn(true);
        when(dbManager.getUserRole(any())).thenReturn(null);
        when(req.getSession()).thenReturn(session);

        signInController.doPost(req, resp);
        verify(resp, atLeastOnce()).sendRedirect(any());
    }
}