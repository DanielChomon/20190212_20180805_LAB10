package com.example.lab10_20190212_20180805.Controllers;

import com.example.lab10_20190212_20180805.Beans.Insurance;
import com.example.lab10_20190212_20180805.Beans.Student;
import com.example.lab10_20190212_20180805.Beans.Trip;
import com.example.lab10_20190212_20180805.Daos.StudentDao;
import com.example.lab10_20190212_20180805.Daos.TripDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "TripServlet", urlPatterns = {"/TripServlet", ""})
public class TripServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");

        RequestDispatcher view;
        StudentDao studentDao = new StudentDao();
        TripDao tripDao = new TripDao();

        switch (action) {
            case "lista":
                Student student = studentDao.obtenerEstudiante(Integer.parseInt(request.getParameter("codigo")));
                request.setAttribute("student", student);
                request.setAttribute("listaViajes", tripDao.listarViajes());
                view = request.getRequestDispatcher("ingresado.jsp");
                view.forward(request, response);
                break;
            case "agregar":
                request.setAttribute("listaViajes", tripDao.listarViajes());
                view = request.getRequestDispatcher("agregar.jsp");
                view.forward(request, response);
                break;

            default:
                response.sendRedirect("TripServlet");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Trip t = new Trip();
        t.setFecha_reserva(request.getParameter("fecha_reserva"));
        t.setFecha_viaje(request.getParameter("fecha_viaje"));
        t.setCiudad_origen(request.getParameter("ciudad_origen"));
        t.setCiudad_destino(request.getParameter("ciudad_destino"));
        t.setBoleto(Integer.parseInt(request.getParameter("boleto")));
        t.setCosto(new Double(request.getParameter("costo")));

        String codigo = request.getParameter("codigo");
        Student student = new Student(Integer.parseInt(codigo));
        t.setCodigo_estudiante(student);

        String nombre_empresa = request.getParameter("nombre_empresa");
        Insurance insurance = new Insurance(nombre_empresa);
        t.setSeguro(insurance);

        TripDao tripDao = new TripDao();

        if (request.getParameter("id_viaje") == null) {
            try {
                tripDao.guardarViaje(t);
                response.sendRedirect("TripServlet?msg=Viaje creado exitosamente");
            } catch (SQLException ex) {
                response.sendRedirect("TripServlet?err=Error al crear viaje");
            }
        } else {
            t.setId_viaje(Integer.parseInt(request.getParameter("id_viaje")));
            try {
                tripDao.actualizarViaje(t);
                response.sendRedirect("TripServlet?msg=Viaje actualizado exitosamente");
            } catch (SQLException ex) {
                response.sendRedirect("TripServlet?err=Error al actualizar viaje");
            }
        }

    }

}