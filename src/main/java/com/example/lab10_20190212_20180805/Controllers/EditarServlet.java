package com.example.lab10_20190212_20180805.Controllers;

import com.example.lab10_20190212_20180805.Beans.Insurance;
import com.example.lab10_20190212_20180805.Beans.Student;
import com.example.lab10_20190212_20180805.Beans.Trip;
import com.example.lab10_20190212_20180805.Daos.StudentDao;
import com.example.lab10_20190212_20180805.Daos.TripDao;
import com.mysql.cj.conf.IntegerProperty;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "EditarServlet", value = "/EditarServlet")
public class EditarServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("a") == null ? "editar" : request.getParameter("a");
        TripDao tripdao = new TripDao();
        StudentDao studentDao = new StudentDao();
        switch (action) {
            case "editar" -> {
                int id = Integer.parseInt(request.getParameter("id"));
                Student student = studentDao.obtenerEstudiante(Integer.parseInt(request.getParameter("codigo")));
                Trip viaje = tripdao.obtenerViaje(id);
                request.setAttribute("viaje", viaje);
                request.setAttribute("student", student);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("editar.jsp");
                requestDispatcher.forward(request, response);
            }
            case "borrar" -> {
                int id = Integer.parseInt(request.getParameter("id"));
                tripdao.borrarViaje(id);
                response.sendRedirect(request.getContextPath() + "/TripServlet");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action") == null ? "actualizar" : request.getParameter("a");
        TripDao tripDao = new TripDao();
        switch (action) {
            case "actualizar" -> {
                Trip viaje = leerParametrosRequest(request);
                tripDao.actualizarViaje(viaje);
                response.sendRedirect(request.getContextPath() + "/TripServlet");
            }

        }
    }

    public Trip leerParametrosRequest(HttpServletRequest request) {
        String fecha_r = request.getParameter("fecha_r");
        String fecha_v = request.getParameter("fecha_v");
        String ciudad_o = request.getParameter("ciudad_o");
        String ciudad_d = request.getParameter("ciudad_d");
        String seguro = request.getParameter("seguro");
        int boleto = Integer.parseInt(request.getParameter("boleto"));
        Double costo = Double.parseDouble(request.getParameter("costo"));
        Insurance insurance = new Insurance();
        insurance.setNombre_empresa(seguro);
        Trip viaje = new Trip();
        viaje.setFecha_reserva(fecha_r);
        viaje.setFecha_viaje(fecha_v);
        viaje.setCiudad_origen(ciudad_o);
        viaje.setCiudad_destino(ciudad_d);
        viaje.setSeguro(insurance);
        viaje.setBoleto(boleto);
        viaje.setCosto(costo);

        return viaje;
    }
}
