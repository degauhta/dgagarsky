package ru.job4j;

import org.junit.Test;

/**
 * ScriptsTest class.
 *
 * @author Denis
 * @since 08.06.2017
 */
public class ScriptsTest {
    /**
     * Test scripts.
     */
    @Test
    public void testScripts() {
        String url = "jdbc:postgresql://localhost:5432/";
        String user = "postgres";
        String password = "32167";
        String dbName = "java_from_a_to_z_dega";

        Scripts scripts = new Scripts();
        scripts.connectToServer(url, user, password);
        scripts.createDB(dbName);
        scripts.connectToServer(String.format("%s%s", url, dbName), user, password);
        scripts.createTables();
        scripts.insertTestData();
    }

}