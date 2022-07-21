package com.cydeo.jdbctests.day01;

import java.sql.*;

public class TestOracleConnection {

    public static void main(String[] args) throws SQLException {

        /*
        how to get in real life-
        ask info from your  team to use. basically take it and use it. they will give  you  portnumber,ip,username,password
         */

        String dbUrl = "jdbc:oracle:thin:@54.163.114.102:1521:XE";
        String dbUserName = "hr";
        String dbPassword = "hr";

        //DriverManager class getConnection methid is used to make connection with DB
      Connection conn = DriverManager.getConnection(dbUrl,dbUserName,dbPassword);

        //create statement from connection to run  queries
        Statement statement = conn.createStatement();

        //using with  statement we will execute query

       ResultSet rs = statement.executeQuery("select * from regions");//we only need to know execute query.

       //move cursor  to  first row
       rs.next();
       //now we are  at  first row. if there is no next row it will return false and stop. so we can use while loop to check all options.

//        while (rs.next()){
//            System.out.println(rs.getInt(1) + "-" +rs.getString(2));
//        }

        System.out.println("rs.getString(\"region_name\") = "  +  rs.getString("region_name"));
        System.out.println("rs.getString(2) = " + rs.getString(2));
        //get string we can use to get the information from the table. can either get it with a string of the column name or with index.


        //get info from  americas

        rs.next();
        System.out.println("rs.getString(\"region_name\") = " + rs.getString("region_name"));

        rs.next();
        System.out.println(rs.getString(2));


        //close connection
        rs.close();
        statement.close();
        conn.close();
    }
}
