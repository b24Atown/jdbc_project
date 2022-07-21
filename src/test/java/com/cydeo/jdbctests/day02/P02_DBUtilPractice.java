package com.cydeo.jdbctests.day02;

import com.cydeo.jdbctests.utility.DB_Util;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class P02_DBUtilPractice {

    @Test
    public void task1(){
        //create connection
        DB_Util.createConnection();
        //run query
        DB_Util.runQuery("select first_name,last_name,salary,job_id from employees where rownum<6");
        //get all data as list of map
        List<Map<String,String>> allRowAsListOfMap = DB_Util.getAllRowAsListOfMap();
        //print each row into screen
        for(Map<String,String> eachRow: allRowAsListOfMap){
            System.out.println(eachRow);
        }

        //close connection
        DB_Util.destroy();
    }

    @Test
    public void task2(){

        DB_Util.createConnection();
        DB_Util.runQuery("select first_name,last_name,salary,job_id from employees where rownum<6");

        //get first row as map
        Map<String,String> rowMap =  DB_Util.getRowMap(1);
        System.out.println(rowMap);

        //print steven in first row
        System.out.println(DB_Util.getFirstRowFirstColumn());//steven
        System.out.println(DB_Util.getCellValue(1, 1));




        DB_Util.destroy();
    }
}
