package com.cydeo.jdbctests.day01;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class P03_MetadataTest {
    String dbUrl = "jdbc:oracle:thin:@54.163.114.102:1521:XE";
    String dbUserName = "hr";
    String dbPassword = "hr";

    @Test
    public void task1() throws SQLException {


        Connection conn = DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
        Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = statement.executeQuery("select * from regions");

        //database metadata - info about database'

        DatabaseMetaData dbMetaData = conn.getMetaData();

        System.out.println(dbMetaData.getUserName());//hr
        System.out.println(dbMetaData.getDatabaseProductName());//Oracle
        System.out.println(dbMetaData.getDatabaseProductVersion());//Oracle Database
        System.out.println(dbMetaData.getDriverName());//Oracle JDBC driver
        System.out.println(dbMetaData.getDriverVersion());//19.3.0.0
        //these were just to show some info but none will be needed.

        //ResultSet MetaData
        ResultSetMetaData rsmd = rs.getMetaData();

        //how many column we have
        int columncount = rsmd.getColumnCount();
        System.out.println(columncount);

        //how to get name for second column
        System.out.println(rsmd.getColumnName(2));

        //print all column name dynamically  (use columncount.fori for shortcut)
        for (int i = 1; i <=columncount; i++) {
            System.out.println(rsmd.getColumnName(i));
        }

        System.out.println("-------");

        while (rs.next()){
            for (int i = 1; i <=columncount; i++) {
                System.out.println(rsmd.getColumnName(i) + "-" + rs.getString(i));
            }
        }


        rs.close();
        statement.close();
        conn.close();

    }
}
