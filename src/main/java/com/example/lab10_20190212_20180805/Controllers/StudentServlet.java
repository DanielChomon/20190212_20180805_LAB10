package com.example.lab10_20190212_20180805.Controllers;

import com.example.lab10_20190212_20180805.Daos.Lab10Dao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "StudentServlet", value = "/StudentServlet")
public class StudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("crear.jsp");
        requestDispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action") == null ? "listar" : request.getParameter("action");
        Lab10Dao lab10Dao = new Lab10Dao();
        HttpSession session = request.getSession();
        switch (action) {
            case "crear":
                String nombre = request.getParameter("nombre");
                String apellido = request.getParameter("apellido");
                int edad = Integer.parseInt(request.getParameter("edad"));
                int codigo = Integer.parseInt(request.getParameter("codigo"));
                String correo = request.getParameter("correo");
                String especialidad = request.getParameter("especialidad");
                String contra = request.getParameter("contra");
                String contra1 = request.getParameter("contra1");
                if (contra.equals(contra1)) {
                    if (((int) Math.log10(codigo) + 1) == 8) {
                        if (edad > 17 && edad < 30) {
                            if(validar(nombre)){
                                if(validar(apellido)){
                                    if(validar_contra(contra)){
                                        lab10Dao.crear(nombre, apellido, edad, codigo, correo, especialidad, contra);
                                        response.sendRedirect(request.getContextPath() + "/CrearServlet");
                                    }
                                    else{
                                        session.setAttribute("error", "contraIncorrecto");
                                        response.sendRedirect(request.getContextPath() + "/CrearServlet");
                                    }
                                }
                                else{
                                    session.setAttribute("error", "apellidoIncorrecto");
                                    response.sendRedirect(request.getContextPath() + "/CrearServlet");
                                }
                            }
                            else{
                                session.setAttribute("error", "nombreIncorrecto");
                                response.sendRedirect(request.getContextPath() + "/CrearServlet");
                            }
                        } else {
                            session.setAttribute("error", "edadIncorrecto");
                            response.sendRedirect(request.getContextPath() + "/CrearServlet");
                        }

                    } else {
                        session.setAttribute("error", "codigoIncorrecto");
                        response.sendRedirect(request.getContextPath() + "/CrearServlet");
                    }
                } else {
                    session.setAttribute("error", "contraNoCoinciden");
                    response.sendRedirect(request.getContextPath() + "/CrearServlet");
                }
                break;
        }
    }

    public static boolean validar(String a){
        boolean valido = true;
        if(a.substring(0).matches("[0-9]")){
            valido = false;
        }
        return valido;
    }
    public static boolean validar_contra(String a){
        boolean valido = false;
        int b = a.length();
        if(a.substring(0,b).matches("[0-9]")){
            valido = true;
        }
        if(a.substring(0,b).matches("[A-Z]")){
            valido = true;
        }
        if(a.substring(0,b).matches("[@#$%^&-+=()]")){
            valido = true;
        }
        return valido;
    }
}

