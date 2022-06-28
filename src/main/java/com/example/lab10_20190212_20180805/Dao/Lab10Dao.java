package com.example.lab10_20190212_20180805.Dao;

import sun.tools.jstack.JStack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Lab10Dao extends BaseDao{
    public void crear(String nombre, String apellido, int edad, int codigo, String correo, String especialidad, String contra){
        String sql="insert into estudiante (nombre, apellido, edad, codigo, correo, "+
                "especialdad, contra, status) values (?,?,?,?,?,?,sha2(?,256),?)";
        try(Connection conn= this.getConnection();
            PreparedStatement pstmt= conn.prepareStatement(sql)){
            pstmt.setString(1,nombre);
            pstmt.setString(2,apellido);
            pstmt.setInt(3,edad);
            pstmt.setInt(4,codigo);
            pstmt.setString(5,correo);
            pstmt.setString(6,especialidad);
            pstmt.setString(7,contra);
            pstmt.setString(8,"normal");
            pstmt.executeUpdate();
        }catch(SQLException e) {
            System.out.println("Hubo un error en la conexión!");
            e.printStackTrace();
        }
    }
}
