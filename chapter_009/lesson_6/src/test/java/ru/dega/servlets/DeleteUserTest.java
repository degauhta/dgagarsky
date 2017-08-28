package ru.dega.servlets;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import ru.dega.models.UserRole;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * DeleteUserTest class.
 *
 * @author Denis
 * @since 15.08.2017
 */
@SuppressWarnings({"SqlDialectInspection", "SqlNoDataSourceInspection"})
public class DeleteUserTest {
    /**
     * Before class.
     *
     * @throws SQLException           error
     * @throws ClassNotFoundException error
     * @throws IOException            error
     */
    @BeforeClass
    public static void init() throws SQLException, ClassNotFoundException, IOException {
        Class.forName("org.hsqldb.jdbc.JDBCDriver");
        initDatabase();
    }

    /**
     * Create a connection.
     *
     * @return connection object
     */
    private static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager
                    .getConnection("jdbc:hsqldb:mem:employees;sql.syntax_pgs = true",
                            "vinod", "vinod");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Database initialization for testing.
     *
     * @throws SQLException error
     */
    private static void initDatabase() throws SQLException {
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS USERS (LOGIN TEXT, PASSWORD TEXT, "
                    + "NAME TEXT, EMAIL TEXT, CREATEDATE TIMESTAMP, ROLE TEXT)");
            connection.commit();
            statement.execute("INSERT INTO USERS (LOGIN, PASSWORD, NAME, EMAIL, CREATEDATE, ROLE) "
                    + "VALUES ('test-login', 'test-password', 'test-name', 'email', '2017-01-01 00:00:00', 'USER')");
            connection.commit();
        }
    }

    /**
     * Init.
     *
     * @throws NamingException error
     */
    @Before
    public void initJNDI() throws NamingException {
        SimpleNamingContextBuilder builder = SimpleNamingContextBuilder.emptyActivatedContextBuilder();
        DataSource dataSource = new DriverManagerDataSource("jdbc:hsqldb:mem:employees",
                "vinod", "vinod");
        builder.bind("java:comp/env/jdbc/mvc", dataSource);
    }

    /**
     * After.
     *
     * @throws SQLException           error
     * @throws ClassNotFoundException error
     * @throws IOException            error
     */
    @AfterClass
    public static void destroy() throws SQLException, ClassNotFoundException, IOException {
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE USERS");
            connection.commit();
        }
    }

    /**
     * Delete root.
     *
     * @throws ServletException error
     * @throws IOException      error
     * @throws SQLException     error
     */
    @Test
    public void whenDeleteRootThenError() throws ServletException, IOException, SQLException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        DeleteUser deleteUserServlet = new DeleteUser();

        when(request.getParameter("login")).thenReturn("root");
        when(request.getRequestDispatcher(any())).thenReturn(dispatcher);
        deleteUserServlet.doPost(request, response);

        verify(request, atLeastOnce()).setAttribute("error", "Cant delete user root!");
        verify(dispatcher, atLeastOnce()).forward(request, response);
    }

    /**
     * User have administrator role.
     *
     * @throws ServletException error
     * @throws IOException      error
     * @throws SQLException     error
     */
    @Test
    public void whenUserHaveAdministratorRoleThenDeleteEntry() throws ServletException, IOException, SQLException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        DeleteUser deleteUserServlet = new DeleteUser();

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("login")).thenReturn("test-login");
        when(session.getAttribute("role")).thenReturn(UserRole.USER);
        when(request.getParameter("login")).thenReturn("test-login");
        when(request.getContextPath()).thenReturn("");

        deleteUserServlet.doPost(request, response);
        boolean actual = true;
        String sql = "SELECT * FROM USERS;";

        try (Statement statement = getConnection().createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                actual = false;
            }
        }
        assertThat(actual, is(true));
    }

    /**
     * User have try delete other user.
     *
     * @throws ServletException error
     * @throws IOException      error
     * @throws SQLException     error
     */
    @Test
    public void whenUserTryDeleteOtherUserThenError() throws ServletException, IOException, SQLException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        DeleteUser deleteUserServlet = new DeleteUser();

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("login")).thenReturn("other-login");
        when(session.getAttribute("role")).thenReturn(UserRole.USER);
        when(request.getParameter("login")).thenReturn("test-login");
        when(request.getRequestDispatcher(any())).thenReturn(dispatcher);
        when(request.getContextPath()).thenReturn("");

        deleteUserServlet.doPost(request, response);

        verify(request, atLeastOnce()).setAttribute("error", "User can delete only themselves!");
        verify(dispatcher, atLeastOnce()).forward(request, response);
    }
}