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
import java.io.IOException;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * UsersViewTest class.
 *
 * @author Denis
 * @since 28.08.2017
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(DBManager.class)
public class UsersViewTest {
    /**
     * Test.
     *
     * @throws ServletException error
     * @throws IOException      error
     */
    @Test
    public void test() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        DBManager dbManager = mock(DBManager.class);

        UsersView usersView = new UsersView();


        PowerMockito.mockStatic(DBManager.class);
        BDDMockito.given(DBManager.getInstance()).willReturn(dbManager);
        when(req.getRequestDispatcher(any())).thenReturn(requestDispatcher);


        usersView.doGet(req, resp);
        verify(requestDispatcher, atLeastOnce()).forward(req, resp);

//        req.setAttribute("users", DBManager.getInstance().getAllEntries());
//        req.setAttribute("roles", UserRole.values());
//        req.getRequestDispatcher("/WEB-INF/views/UsersView.jsp").forward(req, resp);


    }
}