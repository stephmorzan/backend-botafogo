<%-- 
    Document   : main
    Created on : 24/11/2016, 02:29:26 PM
    Author     : EQ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Botafogo: Login</title>
<link rel="stylesheet" href="css/foundation.css">
<link rel="stylesheet" href="css/foundation.min.css">
</head>
<body>
	<div class="row">
		<div class="columns large-12">
			<h1>Bienvenid@</h1>
		</div>
	</div>
	<br>
	<div class="row">
		<div class="large-12 columns"><h6>Inicia sesión</h6>
			<br>
            </div>
        </div>
        <br>
        <div class="row">
            <form action="login" method="post">
                <div class="columns column large-8 large-offset-2 small-12">
                    <p>Usuario: <input type="text"></p>
                    <p>Contraseña: <input type="text"></p>

                    <input type="submit" value="Ingresar">
                </div>
            </form>
        </div>
        <br>
        <br>
        <div class="row">
            <a href="signup.jsp">¿No tienes cuenta aún? Regístrate.</a>
        </div>
    </body>
</html>
