package vote;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class printTable {
    private DBManipulations dbManipulations;

    public printTable() {
        this.dbManipulations = new DBManipulations();
    }

    void printResult(String tableName) {
        try {
            Connection con = dbManipulations.getConnection();
            ResultSet rs = con.createStatement().executeQuery("select * from " + tableName);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(rsmd.getColumnName(i) + "\t");
            }
            System.out.println();
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(rs.getString(i) + "\t");
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        System.out.println();
    }
}
