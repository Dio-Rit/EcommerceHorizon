<%-- 
    Document   : Venda
    Created on : 16 de mai de 2021, 09:02:20
    Author     : yNot
--%>

<%@page import="DAO.VendaDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <%

            byte[] bytes = new VendaDAO().gerarRelatorio(request.getParameter("DataIni"), request.getParameter("DataFini"));

            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream outStream = response.getOutputStream();
            outStream.write(bytes, 0, bytes.length);
            outStream.flush();
            outStream.close();

        %>
    </body>
</html>
