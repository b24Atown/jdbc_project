package com.cydeo.jdbctests.day02;

import com.cydeo.jdbctests.utility.DB_Util;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class P03_libraryDBTest {

    // Create connection to MySQL Database
    String url = "jdbc:mysql://54.157.236.232:3306/library2";
    String username =  "library2_client";
    String password =  "6s2LQQTjBcGFfDhY" ;


    @Test
    public void testUserCount(){

        DB_Util.createConnection(url,username,password);

        DB_Util.runQuery("select count(*) from users");

        //get result from db
        String expectedCount = DB_Util.getCellValue(1,1);

        String actualCount = "7364";

        Assertions.assertEquals(expectedCount,actualCount);

        DB_Util.destroy();

    }

    @Test
    public void task2(){
        DB_Util.createConnection(url,username,password);
        DB_Util.runQuery("select count(*) from books");
        String expectedCount = DB_Util.getCellValue(1,1);
        String actualCount = "23339";
        Assertions.assertEquals(expectedCount,actualCount);
        DB_Util.destroy();

    }

}
