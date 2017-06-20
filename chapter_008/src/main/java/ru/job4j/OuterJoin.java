package ru.job4j;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * OuterJoin class.
 *
 * @author Denis
 * @since 15.06.2017
 */
class OuterJoin {
    /**
     * Connection.
     */
    private Connection connection;

    /**
     * Default constructor.
     *
     * @param connection connection
     */
    OuterJoin(Connection connection) {
        this.connection = connection;
    }

    /**
     * Create tables in DB.
     */
    @SuppressWarnings("SqlNoDataSourceInspection")
    void createTables() {
        Statement statement;
        String sql;
        try {
            sql = "CREATE TABLE ENGINES "
                    + "(ID SERIAL PRIMARY KEY, "
                    + " NAME CHARACTER(1000) NOT NULL);"

                    + "CREATE TABLE TRANSMISSIONS "
                    + "(ID SERIAL PRIMARY KEY, "
                    + " NAME CHARACTER(1000) NOT NULL);"

                    + "CREATE TABLE GEARBOXES "
                    + "(ID SERIAL PRIMARY KEY, "
                    + " NAME CHARACTER(1000) NOT NULL);"

                    + "CREATE TABLE CARS "
                    + "(ID SERIAL PRIMARY KEY, "
                    + " NAME CHARACTER(1000) NOT NULL, "
                    + " ENGINE_ID INTEGER REFERENCES ENGINES(ID), "
                    + " TRANSMISSION_ID INTEGER REFERENCES TRANSMISSIONS(ID), "
                    + " GEARBOX_ID INTEGER REFERENCES GEARBOXES(ID));";

            statement = this.connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Insert test data in tables.
     */
    @SuppressWarnings({"SqlNoDataSourceInspection", "SqlResolve"})
    void insertTestData() {
        Statement statement;
        String sql;
        try {
            statement = this.connection.createStatement();
            sql = "INSERT INTO ENGINES (NAME) "
                    + "VALUES ('Ford engine 1'), ('Ford engine 2'), ('Volkswagen engine 3'),"
                    + "('Volkswagen engine 4'), ('Audi engine 5'), ('Audi engine 6');"

                    + "INSERT INTO TRANSMISSIONS (NAME) "
                    + "VALUES ('Ford transmission 1'), ('Volkswagen transmission 2'), "
                    + "('Audi transmission 3'), ('BMW transmission 4');"

                    + "INSERT INTO GEARBOXES (NAME) "
                    + "VALUES ('Ford gearbox 1'), ('Ford gearbox 2'), ('Ford gearbox 3'), "
                    + "('Audi gearbox 4'), ('Volvo gearbox 5'), ('Volkswagen gearbox 6');"

                    + "INSERT INTO CARS (NAME, ENGINE_ID, TRANSMISSION_ID, GEARBOX_ID) "
                    + "VALUES ('Ford Focus', 1, 1, 1), ('Volkswagen Golf', 3, 2, 6), ('Audi A4', 5, 3, 4);";

            statement.executeUpdate(sql);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Select cars.
     */
    @SuppressWarnings({"SqlNoDataSourceInspection", "SqlResolve", "MalformedFormatString"})
    void selectCars() {
        String sql;
        String car;
        String engine;
        String gearbox;
        String transmission;
        try (Statement statement = this.connection.createStatement()) {
            sql = "SELECT c.name AS car, e.name AS engine, gb.name AS gearbox, tr.name AS transmission "
                    + "FROM cars AS c LEFT OUTER JOIN engines AS e ON c.engine_id = e.id "
                    + "LEFT OUTER JOIN gearboxes AS gb ON c.gearbox_id = gb.id "
                    + "LEFT OUTER JOIN transmissions AS tr ON c.transmission_id = tr.id;";
            try (ResultSet rs = statement.executeQuery(sql)) {
                while (rs.next()) {
                    car = rs.getString("car");
                    engine = rs.getString("engine");
                    gearbox = rs.getString("gearbox");
                    transmission = rs.getString("transmission");
                    System.out.println(String.format("car=%s, engine=%s, gearbox=%s, transmission=%s",
                            car.replaceAll("\\s+$", ""), engine.replaceAll("\\s+$", ""),
                            gearbox.replaceAll("\\s+$", ""), transmission.replaceAll("\\s+$", "")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Select stock item.
     *
     * @param name      name of item field
     * @param tableName table name
     */
    @SuppressWarnings({"SqlNoDataSourceInspection", "SqlResolve", "MalformedFormatString"})
    void selectStockItem(String name, String tableName) {
        String sql;
        String item;
        try (Statement statement = this.connection.createStatement()) {
            sql = String.format("SELECT i.name AS %s FROM %s AS i "
                    + "LEFT OUTER JOIN cars AS c ON i.id = c.%s_id where c.id is null", name, tableName, name);
            try (ResultSet rs = statement.executeQuery(sql)) {
                while (rs.next()) {
                    item = rs.getString(name);
                    System.out.println(String.format("stock %s=%s", name, item.replaceAll("\\s+$", "")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}