<%-- 
    Document   : AtualizaProduto
    Created on : 11 de abr de 2021, 14:03:23
    Author     : yNot
--%>

<%@page import="Entidade.Produto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Horizon - Editar Produto</title>
        
        <style>
            .bd-placeholder-img {
                font-size: 1.125rem;
                text-anchor: middle;
                -webkit-user-select: none;
                -moz-user-select: none;
                user-select: none;
            }

            @media (min-width: 768px) {
                .bd-placeholder-img-lg {
                    font-size: 3.5rem;
                }
            }

        </style>

        <!-- Bootstrap core CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <link href="css/navbar.css" rel="stylesheet">
        
        <script language="JavaScript" src="js/ValidaProduto.js"></script>
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

                        <form action="DAOProduto/ListarProduto.jsp">
                            <button type="submit" class="btn btn-dark">Voltar</input>
                        </form>

                    </div>
                </div>
            </div>
        </nav>

        <div id="EditarProduto">

            <form name="AcaoProduto" id="AcaoProduto" method="post" action="AcaoProduto?param=EditarProduto" onSubmit="return validardadosProduto();">

                <% Produto usu = (Produto) request.getAttribute("objProduto");%>

                <center>

                    <input type="hidden" id="id" name="id" value="<%= usu.getId()%>">

                    <div class="form-group col-md-3">
                        <label for="Nome">Nome</label>
                        <input type="text" class="form-control" id="nome" name="nome" aria-describedby="nome" required="" value="<%= usu.getNome()%>">
                    </div>

                    <div class="form-group col-md-3">
                        <label for="Quantidade">Quantidade</label>
                        <input type="text" class="form-control" id="quantidade" name="quantidade" aria-describedby="quantidade" placeholder="Digite a quantidade" required="" value="<%= usu.getQuantidade()%>" data-mask="00000000000000000000">
                    </div>

                    <div class="form-group col-md-3">
                        <label for="preco">Preço</label>
                        <input type="text" class="form-control" id="preco" name="preco" aria-describedby="preco" placeholder="0000.00" required="" value="<%= usu.getPreco()%>" data-mask="##.##">
                    </div>
                    
                    <div class="form-group col-md-3">
                        <label for="Descricao">Descrição</label>
                        <input type="text" class="form-control" id="descricao" name="descricao" aria-describedby="descricao" placeholder="Digite uma descricao" required="" value="<%= usu.getDescricao()%>">
                    </div>

                    <br>
                    <button type="submit" class="btn btn-dark">Atualizar</button>

                </center>
            </form>
        </div>

        <script src="js/bootstrap.bundle.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js" ></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.min.js"></script>

    </body>
</html>
