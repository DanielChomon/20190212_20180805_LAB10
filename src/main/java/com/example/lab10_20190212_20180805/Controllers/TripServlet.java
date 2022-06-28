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
import java.math.BigDecimal;
import java.sql.SQLException;

@WebServlet(name = "TripServlet", urlPatterns = {"/TripServlet", ""})
public class TripServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");

        RequestDispatcher view;
        StudentDao studentDao = new StudentDao();
        TripDao jobDao = new TripDao();

        switch (action) {
            case "lista":
                request.setAttribute("listaEmpleados", tripDao.listarViajes());
                request.setAttribute("listaEmpleadosPorDepas",tripDao.obtenerViajesPorCodigo());
                view = request.getRequestDispatcher("employees/lista.jsp");
                view.forward(request, response);
                break;
            case "agregar":
                request.setAttribute("listaViajes", tripDao.listarViajes());
                request.setAttribute("listaDepartamentos", insuranceDao.listaSeguros());
                request.setAttribute("listaAlumnos", studentDao.listarAlumnos());

                view = request.getRequestDispatcher("trips/formularioNuevo.jsp");
                view.forward(request, response);
                break;
            case "editar":
                if (request.getParameter("id_Viaje") != null) {
                    String tripIdString = request.getParameter("id_Viaje");
                    int Id_Viaje = 0;
                    try {
                        Id_Viaje = Integer.parseInt(tripIdString);
                    } catch (NumberFormatException ex) {
                        response.sendRedirect("EmployeeServlet");
                    }

                    Trip tri = tripDao.obtenerViaje(Id_Viaje);

                    if (tri != null) {
                        request.setAttribute("viaje", tri);
                        request.setAttribute("listaAlumnos", jobDao.listarAlumnos());
                        request.setAttribute("listaSeguros", insuranceDao.listaDepartamentos());
                        view = request.getRequestDispatcher("trips/formularioEditar.jsp");
                        view.forward(request, response);
                    } else {
                        response.sendRedirect("TripServlet");
                    }

                } else {
                    response.sendRedirect("TripServlet");
                }

                break;
            case "borrar":
                if (request.getParameter("id") != null) {
                    String employeeIdString = request.getParameter("id");
                    int employeeId = 0;
                    try {
                        employeeId = Integer.parseInt(employeeIdString);
                    } catch (NumberFormatException ex) {
                        response.sendRedirect("EmployeeServlet?err=Error al borrar el empleado");
                    }

                    Trip tri = tripDao.obtenerViaje(Id_Viaje);

                    if (tri != null) {
                        try {
                            tripDao.borrarEmpleado(employeeId);
                            response.sendRedirect("TripServlet?msg=Viaje borrado exitosamente");
                        } catch (SQLException e) {
                            response.sendRedirect("TripServlet?err=Error al borrar el viaje");
                        }
                    }
                }else{
                    response.sendRedirect("TripServlet?err=Error al borrar el viaje");
                }
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
        t.setCosto(new BigDecimal(request.getParameter("costo")));

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