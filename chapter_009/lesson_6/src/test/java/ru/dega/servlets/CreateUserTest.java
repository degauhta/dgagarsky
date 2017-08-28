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
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * CreateUserTest class.
 *
 * @author Denis
 * @since 15.08.2017
 */
@SuppressWarnings({"SqlDialectInspection", "SqlNoDataSourceInspection"})
public class CreateUserTest {
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
     * Role is administrator.
     *
     * @throws ServletException error
     * @throws IOException      error
     * @throws SQLException     error
     */
    @Test
    public void whenUserHaveAdministratorRoleThenCreateEntry() throws ServletException, IOException, SQLException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        CreateUser createUserServlet = new CreateUser();

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("role")).thenReturn(UserRole.ADMINISTRATOR);

        when(request.getParameter("name")).thenReturn("test-name");
        when(request.getParameter("password")).thenReturn("test-password");
        when(request.getParameter("login")).thenReturn("test-login");
        when(request.getParameter("email")).thenReturn("test-email");
        when(request.getParameter("role")).thenReturn("ADMINISTRATOR");
        when(request.getContextPath()).thenReturn("");

        createUserServlet.doPost(request, response);

        String sql = "SELECT * FROM USERS;";
        try (Statement statement = getConnection().createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                assertThat(rs.getString("name"), is("test-name"));
                assertThat(rs.getString("password"), is("test-password"));
                assertThat(rs.getString("login"), is("test-login"));
                assertThat(rs.getString("email"), is("test-email"));
                assertThat(rs.getString("role"), is("ADMINISTRATOR"));
            }
        }
    }

    /**
     * Role not administrator.
     *
     * @throws ServletException error
     * @throws IOException      error
     * @throws SQLException     error
     */
    @Test
    public void whenUserNotAdministratorThenReturnError() throws ServletException, IOException, SQLException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        CreateUser createUserServlet = new CreateUser();

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("role")).thenReturn(UserRole.USER);
        when(request.getRequestDispatcher(any())).thenReturn(dispatcher);

        createUserServlet.doPost(request, response);

        verify(request, atLeastOnce()).setAttribute("error", "User cant create new users/admins");
        verify(dispatcher, atLeastOnce()).forward(request, response);
    }
}