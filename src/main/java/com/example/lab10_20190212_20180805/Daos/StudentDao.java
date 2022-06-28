package com.example.lab10_20190212_20180805.Daos;


import com.example.lab10_20190212_20180805.Beans.Insurance;
import com.example.lab10_20190212_20180805.Beans.Student;
import com.example.lab10_20190212_20180805.Beans.Trip;

import java.sql.*;
import java.util.ArrayList;

public class StudentDao extends BaseDao{



    public Student validarUsuarioPassword(String username, String password) {

        Student student = null;

        String sql = "select * from students_credentials where codigo = ? and contra = sha2(?,256);";

        try (Connection connection = this.getConection();
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
