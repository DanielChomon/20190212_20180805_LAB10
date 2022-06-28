package com.example.lab10_20190212_20180805.Dao;

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
            String url = "jdbc:mysql://localhost:3306/";

            return DriverManager.getConnection(url,user,pass);
        }
}

