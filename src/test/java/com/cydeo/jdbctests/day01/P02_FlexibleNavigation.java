package com.cydeo.jdbctests.day01;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class P02_FlexibleNavigation {

    @Test
    public void task1() throws SQLException {
        String dbUrl = "jdbc:oracle:thin:@54.163.114.102:1521:XE";
        String dbUserName = "hr";
        String dbPassword = "hr";

        Connection conn = DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
        Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = statement.executeQuery("select first_name,last_name from employees");

        rs.next();//now looking at first row
        System.out.println(rs.getString(1) + "-" + rs.getString(2));

        rs.next();//now looking at 2nd row
        System.out.println(rs.getString(1)+"-"+rs.getString(2));

        rs.last();//now looking at last row
        System.out.println(rs.getString(1)+"-"+rs.getString(2));

        /*
        ResultSet.TYPE_SCROLL_INSENSITIVE- to be able to scroll your cursor or to jump directly in a certain row
        ResultSet.CONCUR_READ_ONLY-we are saying i dont want to change anything in database, makes the object immutable
         */

        //how to find how many rows we have (were in last row now so it shows row were on)
        int row = rs.getRow();
        System.out.println(row);

        //jump to row 8 - now we jumped to row 8 so it will show row as 8. so to find out how many rows we have we need to use rs.last(), then rs.getRow();
        rs.absolute(8);
        row = rs.getRow();
        System.out.println(row);

        //how to go to previous row (will see 7)
        rs.previous();
        row=rs.getRow();
        System.out.println(row);
        System.out.println(rs.getString(1)+"-"+rs.getString(2));


        //last
        rs.last();
        row = rs.getRow();
        System.out.println(row);

        //beforefirst- using this to jump to first row so then we can loop through the data.
        rs.beforeFirst();

        while (rs.next()){

            System.out.println(rs.getString(1)+"-"+rs.getString(2));
        }

        //jump to first row
        rs.first();
        row=rs.getRow();//should see 1 because were in first row



        rs.close();
        statement.close();
        conn.close();

    }
}
