<%-- 
    Document   : ListarFornecedor
    Created on : 11 de abr de 2021, 14:04:46
    Author     : yNot
--%>

<%@page import="Entidade.Fornecedor"%>
<%@page import="DAO.DAOFornecedor"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Horizon - Listar Fornecedor</title>

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

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">

        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">

        <link href="../css/bootstrap.min.css" rel="stylesheet">

        <link href="../css/navbar.css" rel="stylesheet">
    </head>
    <body>
    <body>
        <nav class="navbar navbar-expand-md navbar-dark bg-dark" aria-label="Fourth navbar example">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">Horizon</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarsExample04" aria-controls="navbarsExample04" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="float-end" id="navbarsExample04">
                    <div class="float-end" id="navbarsExampleDefault">

                        <form action="../home.jsp">
                            <button type="submit" class="btn btn-dark">Voltar</input>
                        </form>

                    </div>
                </div>
            </div>
        </nav>

        <div class="container-fluid">
            <div class="row">
                <main class="col-sm-12 ml-sm-auto col-md-12 " role="main">
                    <div>
                        <section layout:fragment="content">
                            <div class="container">
                                <div class="row">
                                    <div class="col-sm-10">Lista de Fornecedores</div>
                                    <div class="col-sm-1">
                                        <a href="../DAOFornecedor/CadastroFornecedor.jsp" class="btn btn-dark active" role="button"
                                           aria-pressed="true"><i class="fas fa-plus"></i>Adicionar</a>
                                    </div>
                                </div>
                                <div class="row">
                                    <br>
                                </div>

                                <%
                                    ArrayList<Fornecedor> fornecedor = new DAOFornecedor().consultarTodos();
                                %>

                                <div class="row">
                                    <table class="table table-striped ">
                                        <thead>
                                            <tr>
                                                <th scope="col">#</th>
                                                <th scope="col">Nome</th>
                                                <th scope="col">CNPJ</th>
                                                <th scope="col">Telefone</th>
                                                <th scope="col">Email</th>
                                                <th scope="col"></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                for (int i = 0; i < fornecedor.size(); i++) {
                                                    Fornecedor categ = fornecedor.get(i);
                                                    if (categ.getX().equals("A")) {
                                            %>
                                            <tr>
                                                <td><%= categ.getId()%></td>
                                                <td><%= categ.getNome()%></td>
                                                <td><%= categ.getCnpj()%></td>
                                                <td><%= categ.getTelefone()%></td>
                                                <td><%= categ.getEmail()%></td>
                                                <td>
                                                    <a href="../AcaoFornecedor?param=EdFornecedor&id=<%= categ.getId()%>" class="btn btn-success" title="Editar" >
                                                        <i class="fas fa-edit"></i>
                                                    </a>
                                                    <a href="../AcaoFornecedor?param=ExcluirFornecedor&id=<%= categ.getId()%>" class="btn btn-danger" title="Excluir">
                                                        <i class="fas fa-trash"></i>
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

        <script src="../js/bootstrap.bundle.min.js"></script>

    </body>
</html>
