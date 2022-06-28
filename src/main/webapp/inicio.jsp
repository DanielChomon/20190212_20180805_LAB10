<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel='stylesheet' href='<%=request.getContextPath()%>/resources/css/bootstrap.min.css'/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.0/font/bootstrap-icons.css">
        <style>
            .bd-placeholder-img {
                font-size: 1.125rem;
                text-anchor: middle;
                -webkit-user-select: none;
                -moz-user-select: none;
                user-select: none;
            }

            @media (min-width: 768px) {
                .bd-placeholder-img-lg {
                    font-size: 3.5rem;
                }
            }
        </style>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/signin.css"/>
    </head>
    <body>
        <body class="text-center">
            <main class="form-signin">
                <form method="post" action="<%=request.getContextPath()%>/LoginServlet">

                    <h1 class="h3 mb-3 fw-normal">Lab 10</h1>
                    <h1 class="h3 mb-3 fw-normal">Bienvenido TeleViajero</h1>

                    <div class="form-floating">
                        <input type="text" class="form-control" id="floatingInput" name="username"
                               placeholder="Código PUCP">
                        <label for="floatingInput">Código PUCP</label>
                    </div>
                    <div class="form-floating">
                        <input type="password" class="form-control" id="floatingPassword" name="password"
                               placeholder="Contraseña">
                        <label for="floatingPassword">Contraseña</label>
                    </div>
                    <% if (request.getParameter("error") != null) { %>
                    <div class="text-danger mb-2">Error en usuario o contraseña</div>
                    <%}%>
                    <button class="w-100 btn btn-lg btn-primary" type="submit">Sign in</button>
                    <p class="mt-5 mb-3 text-muted">© 2017–2021</p>
                </form>
            </main>
        </body>
    </body>
</html>