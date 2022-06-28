package com.example.lab10_20190212_20180805.Daos;


import com.example.lab10_20190212_20180805.Beans.Insurance;
import com.example.lab10_20190212_20180805.Beans.Student;
import com.example.lab10_20190212_20180805.Beans.Trip;

import java.sql.*;
import java.util.ArrayList;

public class StudentDao extends BaseDao{

    public Student obtenerEstudiante(int codigo) {

        Student student = null;

        String sql = "SELECT * FROM estudiante e WHERE codigo = ?";

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {

            pstmt.setInt(1, codigo);

            try (ResultSet rs = pstmt.executeQuery();) {

                if (rs.next()) {
                    student = new Student();
                    student.setNombre(rs.getString(1));
                    student.setApellido(rs.getString(2));
                    student.setEdad(rs.getInt(3));
                    student.setCodigo(rs.getInt(4));
                    student.setCorreo(rs.getString(5));
                    student.setEspecialidad(rs.getString(6));
                    student.setContrasena(rs.getString(7));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return student;
    }

    public Student validarUsuarioPassword(String username, String password) {

        Student student = null;

        String sql = "select * from students_credentials where codigo = ? and contra = sha2(?,256);";

        try (Connection connection = this.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql);) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery();) {
                if (rs.next()) {
                    student = this.obtenerEstudiante(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return student;
    }

}
