<%-- 
    Document   : variosRegistros
    Created on : 18-oct-2018, 2:32:12
    Author     : FranciscoAntonio
--%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="es.albarregas.beans.Ave"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Resultados</title>
        <link rel="stylesheet" type="text/css" href="estilo.css" media="screen" />
    </head>
    <body>
        <div id="contenido">
            <h2>Listado de todas las aves de la base de datos</h2>
            <table>
                <%
                    List<Ave> listado = null;
                    listado = new ArrayList();
                    listado = (ArrayList<Ave>) request.getAttribute("lista");
                    for (Ave pajaro : listado) {
                %>
                <tr>
                    <td><%=pajaro.getAnilla()%></td>
                    <td><%=pajaro.getEspecie()%></td>
                    <td><%=pajaro.getLugar()%></td>
                    <td><%=pajaro.getFecha()%></td>
                </tr>
                <%
                    }
                %>
            </table>



            <br />
            <p><a href="<%= request.getContextPath()%>">Volver</a></p>
        </div>
    </body>
</html>
