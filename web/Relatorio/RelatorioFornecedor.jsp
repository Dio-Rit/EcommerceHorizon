<%-- 
    Document   : RelatorioFuncionario
    Created on : 3 de mai de 2021, 19:14:01
    Author     : yNot
--%>

<%@page import="DAO.DAOFornecedor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
                <%
            byte[] bytes = new DAOFornecedor().gerarRelatorio();

            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream outStream = response.getOutputStream();
            outStream.write(bytes, 0, bytes.length);
            outStream.flush();
            outStream.close();
        %>
    </body>
</html>
