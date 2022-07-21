package com.cydeo.jdbctests.day01;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class P01_JDBCIntro {

    @Test
    public void task1() throws SQLException {
        String dbUrl = "jdbc:oracle:thin:@54.163.114.102:1521:XE";
        String dbUserName = "hr";
        String dbPassword = "hr";
        Connection conn = DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
        Statement statement = conn.createStatement();

        ResultSet rs = statement.executeQuery("select * from departments where manager_id is not null ");

        //print all results from the query to look like this : 10-administration-200-1700
        while (rs.next()){
            System.out.println(rs.getInt(1) + "-" + rs.getString("department_name")+ "-"+ rs.getInt(3) + "-" + rs.getInt(4));
        }





        rs.close();
        statement.close();
        conn.close();
    }

}
