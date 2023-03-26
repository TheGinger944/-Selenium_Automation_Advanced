package DatabasesTesting;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DisplayWithE {
    static Connection con = null;
    private static Statement stmt;
    public static String DB_URL = "jdbc:mysql://localhost:3306/user";
    public static String DB_USER = "root";
    public static String DB_PASSWORD = "root";

    @Before
    public void setUp() throws Exception {
        try {
            String dbClass = "com.mysql.cj.jdbc.Driver";
            Class.forName(dbClass).newInstance();
            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            stmt = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() {
        try {
            String query = "select * from test where title like '%e%'";
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                System.out.printf("%-4s%-14s%s%n", res.getString(1), res.getString(2), res.getString(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() throws Exception {
        if (con != null) {
            con.close();
        }
    }
}
