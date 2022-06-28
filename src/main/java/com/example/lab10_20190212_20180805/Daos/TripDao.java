package com.example.lab10_20190212_20180805.Daos;

import com.example.lab10_20190212_20180805.Beans.Insurance;
import com.example.lab10_20190212_20180805.Beans.Trip;
import com.example.lab10_20190212_20180805.Beans.Student;
import com.example.lab10_20190212_20180805.Dtos.ViajesPorCodigoDto;

import java.sql.*;
import java.util.ArrayList;

public class TripDao extends BaseDao {

    public ArrayList<Trip> listarViajes() {
        ArrayList<Trip> listaViajes = new ArrayList<>();

        try (Connection conn = this.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM viaje v \n"
                     + "left join estudiante e on (v.codigo_estudante = e.codigo) \n"
                     + "left  join seguro s on (v.nombre_seguro = s.nombre_empresa)\n"
                     + "where v.codigo_estudiante = ?");) {

            while (rs.next()) {
                Trip trip = new Trip();
                trip.setFecha_reserva(rs.getString(1));
                trip.setFecha_viaje(rs.getString(2));
                trip.setCiudad_origen(rs.getString(3));
                trip.setCiudad_destino(rs.getString(4));
                trip.setBoleto(rs.getInt(5));
                trip.setCosto(rs.getBigDecimal(6));

                Student student = new Student();
                student.setCodigo(rs.getInt(7));
                trip.setCodigo_estudiante(student);

                Insurance insurance = new Insurance();
                insurance.setNombre_empresa(rs.getString(8));

                trip.setSeguro(insurance);

                listaViajes.add(trip);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listaViajes;
    }

    public Trip obtenerViaje(int id_viaje) {

        Trip trip = null;

        String sql = "SELECT * FROM viaje v WHERE id_viaje= ?";

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {

            pstmt.setInt(1, id_viaje);

            try (ResultSet rs = pstmt.executeQuery();) {

                if (rs.next()) {
                    trip = new Trip();
                    trip.setFecha_reserva(rs.getString(1));
                    trip.setFecha_viaje(rs.getString(2));
                    trip.setCiudad_origen(rs.getString(3));
                    trip.setCiudad_destino(rs.getString(4));
                    trip.setBoleto(rs.getInt(5));
                    trip.setCosto(rs.getBigDecimal(6));

                    Student student = new Student();
                    student.setCodigo(rs.getInt(7));
                    trip.setCodigo_estudiante(student);

                    Insurance insurance = new Insurance();
                    insurance.setNombre_empresa(rs.getString(8));

                    trip.setSeguro(insurance);


                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return trip;
    }

    public void guardarViaje(Trip trip) throws SQLException {

        String sql = "INSERT INTO viajes (fecha_reserva, fecha_viaje, ciudad_origen, ciudad_destino, num_boleto, costo, codigo_estudiante, nombre_seguro) "
                + "VALUES (now(), ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            this.setTripParams(pstmt, trip);
            pstmt.executeUpdate();
        }
    }

    public void actualizarViaje(Trip trip) throws SQLException {

        String sql = "UPDATE viaje SET fecha_reserva = ?, fecha_viaje = ?, ciudad_origen = ?, ciudad_destino = ?, "
                + "num_boleto = ?, costo = ?, cosigo_estudiante = ?, nombre_seguro = ? WHERE id_viaje = ?";

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            this.setTripParams(pstmt, trip);
            pstmt.setInt(9, trip.getId_viaje());
            pstmt.executeUpdate();
        }
    }

    public void borrarViaje(int id_viaje) throws SQLException {
        String sql = "DELETE FROM employees WHERE employee_id = ?";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1, id_viaje);
            pstmt.executeUpdate();
        }
    }

    private void setTripParams(PreparedStatement pstmt, Trip trip) throws SQLException {
        pstmt.setString(1, trip.getFecha_reserva());
        pstmt.setString(2, trip.getFecha_viaje());
        pstmt.setString(3, trip.getCiudad_origen());
        pstmt.setString(4, trip.getCiudad_destino());
        pstmt.setInt(5, trip.getBoleto());
        pstmt.setBigDecimal(7, trip.getCosto());

        pstmt.setInt(6, trip.getCodigo_estudiante().getCodigo());

        pstmt.setString(7, trip.getSeguro().getNombre_empresa());
    }


    public ArrayList<ViajesPorCodigoDto> obtenerViajesPorCodigos() {

        ArrayList<ViajesPorCodigoDto> lista = new ArrayList<>();

        String sql = "select e.codigo, sum(select v.costo from viaje v) as `cantidad` from viaje v\n" +
                "inner join estudiante e on (e.codigo = v.codigo_estudiante) \n" +
                "group by e.codigo order by `cantidad`;";

        try (Connection conn = this.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql);) {

            while (rs.next()) {
                ViajesPorCodigoDto vp = new ViajesPorCodigoDto();
                vp.setCodigo(rs.getInt(1));
                vp.setCantidadViajes(rs.getInt(2));
                lista.add(vp);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }
}
