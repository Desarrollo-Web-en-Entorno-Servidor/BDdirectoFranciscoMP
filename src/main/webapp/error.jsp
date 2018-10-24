<%-- 
    Document   : error
    Created on : 18-oct-2018, 2:29:51
    Author     : FranciscoAntonio
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERROR</title>
        <link rel="stylesheet" type="text/css" href="estilo.css" />
    </head>
    <body>
        <div id="error">
            
            <p><%=request.getAttribute("error")%></p>



            <br />
            <p><a href="<%= request.getContextPath()%>">Volver</a></p>
        </div>
    </body>
</html>
