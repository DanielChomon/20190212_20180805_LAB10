<%--
  Created by IntelliJ IDEA.
  User: kevin
  Date: 27/06/2022
  Time: 20:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
</head>
<body>
<div class="container py-4 h-100">
    <h4 class="my-2">Crear estudiante</h4>
<form method="POST" action="<%=request.getContextPath()%>/CrearServlet?action=crear">
    <div class="form-outline mb-4">
        <label class="form-label" for="nombre">Nombre</label>
        <input name="nombre"
               type="text"
               id="nombre"
               class="form-control"
               placeholder="Ingrese Nombre"/>
    </div>
    <div class="form-outline mb-4">
        <label class="form-label" for="apellido">Apellido</label>
        <input name="apellido"
               type="text"
               id="apellido"
               class="form-control"
               placeholder="Ingrese Apellido"/>
    </div>
    <div class="form-outline mb-4">
        <label class="form-label" for="edad">Edad</label>
        <input name="edad"
               type="number"
               id="edad"
               class="form-control"
               placeholder="Ingrese Edad"/>
    </div>
    <%if(session.getAttribute("error")!=null && session.getAttribute("error").equals("edadIncorrecto")){%>
    <div class="text-danger mb-2">debe ser mayor de edad o menor a 30</div>
    <%}%>
    <div class="form-outline mb-4">
        <label class="form-label" for="codigo">Codigo PUCP</label>
        <input name="codigo"
               type="number"
               id="codigo"
               class="form-control"
               placeholder="Ingrese Codigo PUCP"/>
    </div>
    <%if(session.getAttribute("error")!=null && session.getAttribute("error").equals("codigoIncorrecto")){%>
    <div class="text-danger mb-2">Codigo debe tener 8 dígitos!</div>
    <%}%>
    <div class="form-outline mb-4">
    <label class="form-label" for="correo">Correo PUCP</label>
    <input name="correo"
           type="text"
           id="correo"
           class="form-control"
           placeholder="Ingrese Correo PUCP"/>
    </div>
    <div class="form-outline mb-4">
        <label class="form-label" for="especialidad">Especialidad</label>
        <input name="especialidad"
               type="text"
               id="especialidad"
               class="form-control"
               placeholder="Ingrese especialidad"/>
    </div>
    <div class="form-outline mb-4">
        <label class="form-label" for="contra">Contrasena</label>
        <input name="contra"
               type="text"
               id="contra"
               class="form-control"
               placeholder="Ingrese contrasena"/>
    </div>
    <div class="form-outline mb-4">
        <label class="form-label" for="contra1">Confirmar Contrasena</label>
        <input name="contra1"
               type="text"
               id="contra1"
               class="form-control"
               placeholder="Ingrese contrasena"/>
    </div>
    <%if(session.getAttribute("error")!=null && session.getAttribute("error").equals("contraNoCoinciden")){%>
    <div class="text-danger mb-2">Las contraseñas no coinciden!</div>
    <%}%>
    <button type="submit" class="btn btn-primary">Crear</button>
</form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
</body>
</html>
