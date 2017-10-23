package ru.dega;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * ImportUserTest class.
 *
 * @author Denis
 * @since 05.10.2017
 */
public class ImportUserTest {
    /**
     * Test memory db.
     */
    @Test
    public void whenGetImportedUserFromMemoryThenReturnUser() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        ImportUser importUser = context.getBean(ImportUser.class);
        User firstUser = new User("first");

        importUser.add(firstUser);
        assertThat(importUser.getUser(0), is(firstUser));
    }

    /**
     * Test sql db.
     */
    @Test
    public void whenGetImportedUserFromJDBCThenReturnUser() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        JdbcStorage jdbcStorage = context.getBean(JdbcStorage.class);
        ImportUser importUser = new ImportUser(jdbcStorage);
        User firstUser = new User("first2");
        int id = importUser.add(firstUser);
        assertThat(importUser.getUser(id).getName(), is(firstUser.getName()));
    }
}