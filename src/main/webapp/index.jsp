<%-- 
    Document   : index
    Created on : 18-oct-2018, 2:30:47
    Author     : FranciscoAntonio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Avistamientos</title>
        <link rel="stylesheet" type="text/css" href="estilo.css" />
    </head>
    <body>
        <div id="contenido">
            <h2>Página de avistamientos</h2>
            <form action="buscador" method="post">
                <table>
                    <tr>
                        <td>Introduce una anilla</td>
                        <td><input name="anilla" /></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Anilla" name="unaAnilla"/></td>
                        <td><input type="submit" value="Todas" name="todas"/></td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>
