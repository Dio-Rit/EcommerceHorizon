<%-- 
    Document   : RelatorioVenda
    Created on : 3 de mai de 2021, 19:14:33
    Author     : yNot
--%>

<%@page import="DAO.VendaDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Horizon - Relatório de Vendas</title>
        <link href="../css/bootstrap.min.css" rel="stylesheet">

        <link href="../css/navbar.css" rel="stylesheet">
    </head>
    <body>
        <nav class="navbar navbar-expand-md navbar-dark bg-dark" aria-label="Fourth navbar example">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">Horizon</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarsExample04" aria-controls="navbarsExample04" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="float-end" id="navbarsExample04">
                    <div class="float-end" id="navbarsExampleDefault">
                        <ul class="navbar-nav mr-auto">
                        </ul>

                        <form action="../home.jsp">
                            <button type="submit" class="btn btn-dark">Voltar</input>
                        </form>

                    </div>
                </div>
            </div>
        </nav>
        <div id="CadastroUsuario">
            <form name="RelatorioVenda" id="RelatorioVenda" target="_blank" action="../Relatorios/Venda.jsp">
                <center>
                    <br> 
                    <br> 
                    <div class="form-group col-md-3">
                        <label for="DataIni">Data Inicial</label>
                        <input type="text" class="form-control" id="DataIni" name="DataIni" placeholder="00/00/0000" required="" data-mask="00/00/0000">
                    </div>
                    <div class="form-group col-md-3">
                        <label for="DataFini">Data Final</label>
                        <input type="text" class="form-control" id="DataFini" name="DataFini" placeholder="00/00/0000" required="" data-mask="00/00/0000">
                    </div>
                    <br>
                    <button type="submit" class="btn btn-dark">Gerar Relatório</button>
                </center>
            </form>        
            
        </div>
        <script src="../js/bootstrap.bundle.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js" ></script>"
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.min.js"></script>
    </body>
</html>
