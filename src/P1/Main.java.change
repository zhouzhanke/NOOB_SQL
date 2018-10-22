package com.company;

import java.sql.*;

public class Main
{

	// write your code here
    static final String USER = "zhoudl0605";
    static final String PASS = "980811";

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/test";

    public static void main(String args[])
    {
        SQL sql = new SQL();
        sql.connect(DB_URL, USER, PASS);

        sql.command("SELECT id, name, url FROM websites");
        System.out.println("Goodbye!");
    }
}