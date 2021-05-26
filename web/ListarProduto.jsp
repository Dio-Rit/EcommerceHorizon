<%-- 
    Document   : ListarProduto
    Created on : 16 de mai de 2021, 11:39:45
    Author     : yNot
--%>

<%@page import="DAO.DAOProduto"%>
<%@page import="Entidade.Produto"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>



        <link href="css/bootstrap.min.css" rel="stylesheet">

        <link href="css/navbar.css" rel="stylesheet">
    </head>
    <body>
        <div class="container-fluid">
            <div class="row">
                <main class="col-sm-12 ml-sm-auto col-md-12 " role="main">
                    <div>
                        <br>
                        <section layout:fragment="content">
                            <div class="container">
                                <div class="row">
                                    <div class="col-sm-10">Lista de Produtos</div>
                                </div>
                                <div class="row">
                                    <br>
                                </div>

                                <%
                                    ArrayList<Produto> produto = new DAOProduto().consultarTodos();
                                %>

                                <div class="row">
                                    <table class="table table-striped ">
                                        <thead>
                                            <tr>
                                                <th scope="col">#</th>
                                                <th scope="col">Nome</th>
                                                <th scope="col">Quantidade</th>
                                                <th scope="col">Preço</th>
                                                <th scope="col">Descrição</th>
                                                <th scope="col"></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                for (int i = 0; i < produto.size(); i++) {
                                                    Produto categ = produto.get(i);
                                                    if (categ.getX().equals("A")) {
                                            %>
                                            <tr>
                                                <td><%= categ.getId()%></td>
                                                <td><%= categ.getNome()%></td>
                                                <td><%= categ.getQuantidade()%></td>
                                                <td><%= categ.getPreco()%></td>
                                                <td><%= categ.getDescricao()%></td>
                                                <td>
                                                    <a type="button" href="AcaoVenda?param=SelecionaProduto&id=<%= categ.getId()%>" value="Selecionar" >
                                                        <input type="button" class="btn btn-dark" value="Selecionar">
                                                    </a>

                                                </td>

                                            </tr>
                                            <%
                                                    }
                                                }
                                            %>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </section>
                    </div>
                </main>
            </div>
        </div>

        <script src="js/bootstrap.bundle.min.js"></script>
    </body>
</html>

