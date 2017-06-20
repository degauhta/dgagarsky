package ru.job4j;

import com.google.common.base.Joiner;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * OuterJoinTest class.
 *
 * @author Denis
 * @since 15.06.2017
 */
public class OuterJoinTest {
    /**
     * Outer join.
     */
    private static OuterJoin outerJoin;

    /**
     * Initialization.
     */
    @BeforeClass
    public static void prepareData() {
        Connection connection;
        String url = "jdbc:postgresql://localhost:5432/";
        String user = "postgres";
        String password = "32167";
        String dbName = "car_outer_join_dega";

        Scripts scripts = new Scripts();
        scripts.connectToServer(url, user, password);
        scripts.createDB(dbName);
        connection = scripts.connectToServer(String.format("%s%s", url, dbName), user, password);

        OuterJoin outerJoin = new OuterJoin(connection);
        outerJoin.createTables();
        outerJoin.insertTestData();
        OuterJoinTest.outerJoin = outerJoin;
    }

    /**
     * Select cars test.
     */
    @Test
    public void whenSelectCarsThenMessageCars() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        OuterJoinTest.outerJoin.selectCars();
        String expected = Joiner.on(System.lineSeparator()).join("car=Ford Focus, "
                        + "engine=Ford engine 1, gearbox=Ford gearbox 1, transmission=Ford transmission 1",
                "car=Volkswagen Golf, engine=Volkswagen engine 3, "
                        + "gearbox=Volkswagen gearbox 6, transmission=Volkswagen transmission 2",
                "car=Audi A4, engine=Audi engine 5, "
                        + "gearbox=Audi gearbox 4, transmission=Audi transmission 3", "");
        assertThat(outputStream.toString(), is(expected));
    }

    /**
     * Select engines test.
     */
    @Test
    public void whenSelectStockEnginesThenMessageEngines() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        OuterJoinTest.outerJoin.selectStockItem("engine", "engines");
        String expected = Joiner.on(System.lineSeparator()).join("stock engine=Ford engine 2",
                "stock engine=Volkswagen engine 4", "stock engine=Audi engine 6", "");
        assertThat(outputStream.toString(), is(expected));
    }

    /**
     * Select gearboxes test.
     */
    @Test
    public void whenSelectStockGearBoxesThenMessageGearBoxes() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        OuterJoinTest.outerJoin.selectStockItem("gearbox", "gearboxes");
        String expected = Joiner.on(System.lineSeparator()).join("stock gearbox=Ford gearbox 2",
                "stock gearbox=Ford gearbox 3", "stock gearbox=Volvo gearbox 5", "");
        assertThat(outputStream.toString(), is(expected));
    }

    /**
     * Select transmissions test.
     */
    @Test
    public void whenSelectStockTransmissionsThenMessageTransmissions() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        OuterJoinTest.outerJoin.selectStockItem("transmission", "transmissions");
        String expected = Joiner.on(System.lineSeparator()).join("stock transmission=BMW transmission 4",
                "");
        assertThat(outputStream.toString(), is(expected));
    }
}