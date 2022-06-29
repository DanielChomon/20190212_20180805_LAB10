<%@ page import="com.example.lab10_20190212_20180805.Beans.Trip" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="student" scope="request" type="com.example.lab10_20190212_20180805.Beans.Student" />
<jsp:useBean id="listaviaje" scope="request" type="java.util.ArrayList<com.example.lab10_20190212_20180805.Beans.Trip>" />
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
        <%if(student.getDinero_gastado() < 100){ %>
    <nav class="navbar navbar-dark bg-primary">
            <%}
if(student.getDinero_gastado() >= 100 && student.getDinero_gastado() < 1000){ %>
    <nav class="navbar navbar-light" style="background-color: #C0C0C0;">
            <%}
if(student.getDinero_gastado() >= 1000 && student.getDinero_gastado() < 10000){ %>
    <nav class="navbar navbar-light" style="background-color: #FFD700;">
            <%}
if(student.getDinero_gastado() >= 10000){ %>
    <nav class="navbar navbar-light bg-dark">
                    <%}%>
                    <div class="container-fluid">
                        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarsExample08" aria-controls="navbarsExample08" aria-expanded="false" aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon"></span>
                        </button>

                        <div class="collapse navbar-collapse justify-content-md-center" id="navbarsExample08">
                            <ul class="navbar-nav">
                                <li class="nav-item">
                                    <a class="nav-link active" aria-current="page" href="#">Agregar Viajes</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link disabled" aria-current="page" href="#"><%=student.getNombre()+" "+student.getApellido()%></a>
                                </li>
                            </ul>
                        </div>
                    </div>
    </nav>
</head>
<body>

    <div id="main-container">
        <table>
            <thead>
            <tr>
                <th>Identificador de viaje</th>
                <th>Fecha de reserva</th>
                <th>Fecha de viaje</th>
                <th>Ciudad de origen</th>
                <th>Ciudad de destino</th>
                <th>Empresa de seguro</th>
                <th>Numero de boletos</th>
                <th>Costo total</th>
                <th></th>
            </tr>
            </thead>
                <% int a = 1;
                for (Trip viaje : listaviaje ) {%>
            <tr>

                <td><%=viaje.getId_viaje()%></td>
                <td><%=viaje.getFecha_reserva()%></td>
                <td><%=viaje.getFecha_viaje()%></td>
                <td><%=viaje.getCiudad_origen()%></td>
                <td><%=viaje.getCiudad_destino()%></td>
                <td><%=viaje.getSeguro()%></td>
                <td><%=viaje.getBoleto()%></td>
                <td><%=viaje.getCosto()%></td>
                <td>
                    <a href="<%=request.getContextPath()%>/EditarServlet?&id=<%=viaje.getId_viaje()%>&codigo=<%=student.getCodigo()%>">
                        <i class="far fa-edit btn-tele p-2 rounded"></i>
                    </a>
                </td>
            <td>
                <a href="<%=request.getContextPath()%>/EditarServlet?&id=<%=viaje.getId_viaje()%>">
                <button type="button" class="btn-close btn-close-black" aria-label="Close"></button>
                </a>
            </td>

            </table>
    </div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
</body>
</html>

