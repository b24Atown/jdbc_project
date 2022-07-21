package com.cydeo.jdbctests.day02;

import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarOutputStream;

public class P01_ListOfMap {
    String dbUrl = "jdbc:oracle:thin:@54.163.114.102:1521:XE";
    String dbUserName = "hr";
    String dbPassword = "hr";




    @Test
    public void task1() throws SQLException {

        Connection conn = DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
        Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = statement.executeQuery("select first_name,last_name,salary from employees where rownum<6");
        ResultSetMetaData rsmd = rs.getMetaData();

        /*
        this is what it would look like without  rsmd/rs
        Map<String,Object> rowmap2 = new HashMap<>();
        rowmap2.put("first_name","Neena");
        rowmap2.put("last_name","Kochar");
        rowmap2.put("salary",17000);

         */
        rs.next();

        Map<String,Object> rowmap  = new HashMap<>();
        rowmap.put(rsmd.getColumnName(1),rs.getString(1));
        rowmap.put(rsmd.getColumnName(2),rs.getString(2));
        rowmap.put(rsmd.getColumnName(3),rs.getString(3));

        System.out.println("rowmap = " + rowmap);

        rs.next();

        Map<String,Object> rowmap2 = new HashMap<>();
        rowmap2.put(rsmd.getColumnName(1),rs.getString(1));
        rowmap2.put(rsmd.getColumnName(2),rs.getString(2));
        rowmap2.put(rsmd.getColumnName(3),rs.getString(3));

        System.out.println("rowmap2  =  " + rowmap2);

        //it will keep continuing all the way down the table

        //making a list of maps to add all the data in
        List<Map<String,Object>> dataList = new ArrayList<>();
        dataList.add(rowmap);
        dataList.add(rowmap2);

        //how to get data from the list of maps

        System.out.println(dataList.get(0));//get whole first map
        System.out.println(dataList.get(0).get(rsmd.getColumnName(2)));//lastname value
        System.out.println(dataList.get(0).get(rsmd.getColumnName(3)));//salary value
        System.out.println(dataList.get(0).get(rsmd.getColumnName(1)));//firstname value

        rs.close();
        statement.close();
        conn.close();
    }

    @Test
    public void task2() throws SQLException {
        Connection conn = DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
        Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = statement.executeQuery("select first_name,last_name,salary from employees where rownum<6");
        ResultSetMetaData rsmd = rs.getMetaData();

        //doing it dynamically

        //getcolumnCount
        int columnCount = rsmd.getColumnCount();

        //List of map to store all rows
        List<Map<String,Object>> dataList = new ArrayList<>();

        //iterate

        while (rs.next()){
            Map<String,Object> rowmap = new HashMap<>();
            for (int i = 1; i<=columnCount;i++){ //runs columncount 5 times

                rowmap.put(rsmd.getColumnName(i),rs.getString(i));

            }

            dataList.add(rowmap);
        }

        for(Map<String,Object> eachrowMap:dataList){
            System.out.println(eachrowMap);
        }









        rs.close();
        statement.close();
        conn.close();
    }

    @Test
    public void test() throws SQLException {

        Connection conn = DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
        Statement  statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = statement.executeQuery("select * from regions");
        ResultSetMetaData  rsmd = rs.getMetaData();

        int columnCount = rsmd.getColumnCount();
        List<Map<String,String>> dataList = new ArrayList<>();

        while (rs.next()){
            Map<String,String> rowmap =  new HashMap<>();
            for (int i =1;i<=columnCount;i++){
                rowmap.put(rsmd.getColumnName(i),rs.getString(i));
            }
            dataList.add(rowmap);
        }

        for (Map<String,String>  eachrow:dataList){
            System.out.println(eachrow);
        }


    }
}
