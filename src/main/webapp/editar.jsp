<%@ page import="com.example.lab10_20190212_20180805.Beans.Trip" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="student" scope="request" type="com.example.lab10_20190212_20180805.Beans.Student" />
<jsp:useBean id="viaje" scope="request" type="com.example.lab10_20190212_20180805.Beans.Trip" />
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
    <form method="POST" action="<%=request.getContextPath()%>/EditarServlet?action=actualizar">
        <div class="row">
            <div class="form-outline mb-4">
                <label class="form-label" for="fecha_r"
                >Fecha de reserva</label
                >
                <input
                        type="date"
                        name="fecha_r"
                        id="fecha_r"
                        class="form-control"
                        value="<%=viaje.getFecha_reserva()%>"
                        />
            </div>
            <div class="form-outline mb-4">
                <label class="form-label" for="fecha_v"
                >Fecha de viaje</label
                >
                <input
                        type="date"
                        name="fecha_v"
                        id="fecha_v"
                        class="form-control"
                        value="<%=viaje.getFecha_viaje()%>"
                />
            </div>
            <div class="form-outline mb-4">
            <label class="form-label" for="ciudad_o"
            >Ciudad de origen</label
            >
            <input
                    type="text"
                    name="ciudad_o"
                    id="ciudad_o"
                    class="form-control"
                    value="<%=viaje.getCiudad_origen()%>"
            />
        </div>
            <div class="form-outline mb-4">
                <label class="form-label" for="ciudad_d"
                >Ciudad de destino</label
                >
                <input
                        type="text"
                        name="ciudad_d"
                        id="ciudad_d"
                        class="form-control"
                        value="<%=viaje.getCiudad_destino()%>"
                />
            </div>
            <div class="form-outline mb-4 py-4">
                <label class="form-label" for="seguro">Empresa de seguro</label>
                <select id="seguro"
                        class="frm-field required sect"
                        name="seguro">
                    <option value="Selecciona" selected>Selecciona</option>
                    <option value="Rimac">Rimac</option>
                    <option value="Pacifico">Pacifico</option>
                    <option value="La positiva">La positiva</option>
                    <option value="Seguro internacional">Seguro internacional</option>
                    <option value="Otro">Otro</option>
                </select>
            </div>
            <div class="form-outline mb-4">
                <label class="form-label" for="boleto"
                >Numero de boleto</label
                >
                <input
                        type="number"
                        name="boleto"
                        id="boleto"
                        class="form-control"
                        value="<%=viaje.getBoleto()%>"
                />
            </div>
            <div class="form-outline mb-4">
                <label class="form-label" for="costo"
                >Costo total</label
                >
                <input
                        type="number"
                        name="costo"
                        id="costo"
                        class="form-control"
                        value="<%=viaje.getCosto()%>"
                />
            </div>
        </div>

        <div class="row row-cols-3 justify-content-center">
            <input
                    class="btn btn-tele"
                    type="submit"
                    value="Actualizar"
            />
        </div>
    </form>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
</body>
</html>