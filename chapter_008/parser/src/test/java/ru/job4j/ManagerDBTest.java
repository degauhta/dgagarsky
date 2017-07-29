package ru.job4j;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 * ManagerDBTest class.
 *
 * @author Denis
 * @since 25.07.2017
 */
public class ManagerDBTest {
    /**
     * Connection.
     */
    private Connection connection;

    /**
     * Manager database.
     */
    private ManagerDB managerDB;

    /**
     * Database name.
     */
    private Properties prop;

    /**
     * Initialization.
     */
    @Before
    public void init() {
        this.prop = new Properties();
        ClassLoader classLoader = getClass().getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream("app.properties")) {
            prop.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.managerDB = new ManagerDB();
        this.managerDB.connectToServer(prop.getProperty("dbUrl"),
                prop.getProperty("dbUser"), prop.getProperty("dbPassword"));
        this.connection = this.managerDB.getConnection();
        this.managerDB.createTables();
    }

    /**
     * Drop database.
     */
    @After
    public void dropDataBase() {
        this.managerDB.connectToServer(prop.getProperty("dbUrl"),
                prop.getProperty("dbUser"), prop.getProperty("dbPassword"));
        this.connection = this.managerDB.getConnection();
        String sql = String.format("DROP DATABASE IF EXISTS %s", this.prop.getProperty("dbName"));
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test connection.
     */
    @Test
    public void whenGetNullifiedConnectionReturnNull() {
        this.managerDB.setConnection(null);
        assertThat(this.managerDB.getConnection(), is(nullValue()));
    }


    /**
     * Test add vacancy.
     */
    @Test
    public void whenAddVacancyThenReturnTrue() {
        Vacancy vacancy = new Vacancy("test", "test", "test", "test");
        assertThat(this.managerDB.addEntry(vacancy), is(true));
    }

    /**
     * Test find vacancy.
     */
    @Test
    public void whenFindVacancyInDataBaseThenReturnTrue() {
        Vacancy vacancy = new Vacancy("test", "test", "test", "test");
        Boolean actual = this.managerDB.findEntry(vacancy.getTopicDate(),
                vacancy.getTopic(), vacancy.getUrl());
        assertThat(actual, is(true));
    }

    /**
     * Test find vacancy.
     */
    @Test
    public void whenNotFindVacancyInDataBaseThenReturnFalse() {
        Vacancy notExistVacancy = new Vacancy("test1", "test2", "test3", "test4");
        Boolean actual = this.managerDB.findEntry(notExistVacancy.getTopicDate(),
                notExistVacancy.getTopic(), notExistVacancy.getUrl());
        assertThat(actual, is(false));
    }

    /**
     * Test create database.
     */
    @Test
    public void whenDataBaseExistThenReturnTrue() {
        String dbName = this.prop.getProperty("dbName");
        this.managerDB.createDB(dbName);
        assertThat(this.managerDB.checkDBExists(dbName), is(true));
    }
}