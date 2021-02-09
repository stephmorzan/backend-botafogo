<%-- 
    Document   : signup
    Created on : 24/11/2016, 02:29:09 PM
    Author     : EQ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Botafogo: Sign up</title>
        <link rel="stylesheet" href="css/foundation.css">
        <link rel="stylesheet" href="css/foundation.min.css">
    </head>
    <body>
        <div class="row">
            <div class="columns large-12">
                <h1>Regístrate</h1>
            </div>
        </div>
        <br>
        <div class="row"></div>
    </div>
    <h6>Crea una cuenta e inicia sesión automáticamente</h6>
    <br>
    <br>
    <div class="row">
        <form action="signup" method="post">
            <div class="columns column large-8 large-offset-2 small-12">
                <p>Nombre: <input type="text"></p>
                <p>Cuenta de usuario: <input type="text"></p>
                <p>Correo: <input type="text"></p>
                <p>Contraseña: <input type="text"></p>
            </div>
            <input type="submit" value="Ingresar">
        </form>
    </div>
    </body>
</html>
