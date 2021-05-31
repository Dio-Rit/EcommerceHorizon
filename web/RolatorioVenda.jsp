<%-- 
    Document   : RolatorioVenda
    Created on : 31 de mai de 2021, 19:26:01
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

            byte[] bytes = new VendaDAO().gerarRelatorioVenda();
            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream outStream = response.getOutputStream();
            outStream.write(bytes, 0, bytes.length);
            outStream.flush();
            outStream.close();

        %>
    </body>
</html>
