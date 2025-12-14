package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
    Connection conn;
    private static final String URL = "jdbc:sqlite:C:\\_PROJECTS\\java\\eclipse_test\\mydb.db";

    public DB() {
        this.conn = connect();
    }

    public static Connection connect() {
        try {
            Connection d = DriverManager.getConnection(URL);
            // System.out.println(new java.io.File("mydb.db").getAbsolutePath());
            return d;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void query(String sql) {

        try (Statement stmt = this.conn.createStatement()) {

            stmt.execute(sql);
            System.out.println("query done.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
