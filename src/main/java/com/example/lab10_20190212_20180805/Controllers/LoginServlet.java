package com.example.lab10_20190212_20180805.Controllers;

import com.example.lab10_20190212_20180805.Beans.Student;
import com.example.lab10_20190212_20180805.Daos.StudentDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") != null ? request.getParameter("action") : "login";

        HttpSession session = request.getSession();

        switch (action){
            case "login":
                Student student = (Student) session.getAttribute("estudianteLogueado");
                if(student != null && student.getCodigo() != 0){
                    response.sendRedirect(request.getContextPath());
                }else{
                    RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                    rd.forward(request, response);
                }
                break;
            case "logout":
                session.invalidate();
                response.sendRedirect(request.getContextPath());
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        StudentDao studentDao = new StudentDao();

        Student student = studentDao.validarUsuarioPassword(username, password);

        if (student != null) { //existe usuario y password
            HttpSession session = request.getSession();

            student.setDinero_gastado(null);

            session.setAttribute("estudianteLogueado", student);

            session.setMaxInactiveInterval(60 * 10);

            response.sendRedirect(request.getContextPath() + "/StudentServlet");
        } else {
            response.sendRedirect(request.getContextPath() + "/LoginServlet?error");
        }


    }
}
