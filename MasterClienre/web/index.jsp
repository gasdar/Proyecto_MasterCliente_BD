<%-- 
    Document   : index
    Created on : 07-08-2023, 9:22:30
    Author     : Eliulson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Nuevo Cliente</h1>
        <form action="controller" method="POST">
            Identificación: <br>
            <input type="text" name="identificacion" value=""><br>
            Nombres: <br>
            <input type="text" name="nombres" value=""><br>
            Primer Apellido: <br>
            <input type="text" name="apellido1" value=""><br>
            
            <input type="submit" value="Enviar">
            
        </form>
    </body>
</html>
