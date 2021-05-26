<%-- 
    Document   : CadastroProdutosVenda
    Created on : 16 de mai de 2021, 10:37:35
    Author     : yNot
--%>

<%@page import="Apoio.Formatacao"%>
<%@page import="Entidade.Venda"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Horizon - Cadastro de Produtos</title>

        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/navbar.css" rel="stylesheet">
    </head>
    <body>

        <% Venda usu = (Venda) request.getAttribute("objCliente");%>

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
                        <form action="AcaoVenda?param=ExcluirVenda&id=<%= usu.getId()%>">
                            <button type="submit" class="btn btn-dark">Voltar</input>
                        </form>
                    </div>
                </div>
            </div>
        </nav>



        <div id="CadastroVenda">

            <form name="AcaoVenda" id="AcaoVenda" method="post" action="AcaoVenda?param=SalvarProduto"">
                <center>

                    <div class="form-group col-md-3">
                        <label for="Quantidade">Quantidade</label>
                        <input type="text" class="form-control" id="Quantidade"  name="Quantidade" data-mask="00000000000000000000">
                    </div>

                    <br>
                    <div class="form-group col-md-3">
                        <label for="Produto">Produto</label>
                        <input type="text" class="form-control" id="Produto" name="Produto" placeholder="" required="" disabled="">
                    </div>
                    <!-- Button trigger modal -->
                    <button type="button" class="btn btn-dark" data-bs-toggle="modal" data-bs-target="#exampleModal">
                        Selecionar
                    </button>


                    </div>
                    <!-- Modal -->
                    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div style="width: fit-content; margin-left: -15%" class="modal-content">
                                    <%@include file="ListarProduto.jsp" %>
                                </div>
                            </div>
                        </div>
                    </div>

                    <br>
                    <br>
                    <div>
                        <center>
                            <button type="submit" class="btn btn-dark">Adicionar Produto</button>
                        </center>
                    </div>
                </center>
            </form>
            <script src="https://code.jquery.com/jquery-3.6.0.min.js" ></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.min.js"></script>
    </body>
</html>

