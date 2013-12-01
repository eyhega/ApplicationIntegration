<%-- 
    Document   : index
    Created on : 19 nov. 2013, 15:52:34
    Author     : eyheramo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% 
        request.getRequestDispatcher("/MainServlet").forward(request, response);
        %>
    </body>
</html>
