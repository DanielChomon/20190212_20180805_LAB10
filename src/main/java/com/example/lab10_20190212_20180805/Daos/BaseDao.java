package com.example.lab10_20190212_20180805.Daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public abstract class BaseDao {
        public Connection getConnection () throws SQLException {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            String user = "root";
            String pass = "root";
            String database = "mydb";
            String url = "jdbc:mysql://localhost:3306/" + database + "?serverTimezone=America/Lima";;

            return DriverManager.getConnection(url,user,pass);
        }
}

