<%-- 
    Document   : login
    Created on : 24/11/2016, 02:28:45 PM
    Author     : EQ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Botafogo</title>
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
            <form action="processing" method="post">
                <div class="columns">
                    <label>
                        <h6>¿Qué tal? ¿Cómo te ha ido en el día?</h6>

                        <textarea placeholder="Escribe aquí" name="answer"></textarea>
                    </label>
                </div>

                <div class="columns">
                    <input type="submit" value="Enviar respuesta" style="align-content: center"/>
                </div>

            </form>
        </div>
    </body>
</html>
