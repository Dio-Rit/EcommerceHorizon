<%-- 
    Document   : ListarProdutoAjax
    Created on : 8 de jun de 2021, 19:17:20
    Author     : yNot
--%>

<%@page import="DAO.DAOProduto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Entidade.Produto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">

        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
    </head>
    <body>
        <h1>Listagem de Produtos</h1>

        <div class="container-fluid">
            <div class="row">
                <main class="col-sm-12 ml-sm-auto col-md-12 " role="main">
                    <div>
                        <br>
                        <section layout:fragment="content">
                            <div class="container">
                                <div class="row">
                                    <br>
                                </div>
                                <%
                                    String nome = request.getParameter("nome");
                                    String precoInicial = request.getParameter("precoInicial");
                                    String precoFinal = request.getParameter("precoFinal");
                                    String quantidade = request.getParameter("quantidade");

                                    if (precoInicial == "") {

                                        precoInicial = "0";
                                    }

                                    ArrayList<Produto> produto = new DAOProduto().consultar(nome, precoInicial, precoFinal, quantidade);
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
    </body>
</html>
