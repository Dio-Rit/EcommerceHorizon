<%-- 
    Document   : ListarCliente
    Created on : 16 de mai de 2021, 11:39:37
    Author     : yNot
--%>

<%@page import="DAO.DAOCliente"%>
<%@page import="Entidade.Cliente"%>
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
                                    <div class="col-sm-10">Lista de Clientes</div>
                                </div>
                                <div class="row">
                                    <br>
                                </div>

                                <%
                                    ArrayList<Cliente> cliente = new DAOCliente().consultarTodos();
                                %>

                                <div class="row">
                                    <table class="table table-striped ">
                                        <thead>
                                            <tr>
                                                <th scope="col">#</th>
                                                <th scope="col">Nome</th>
                                                <th scope="col">CPF</th>
                                                <th scope="col">Data Nascimento</th>
                                                <th scope="col">Email</th>
                                                <th scope="col">Descrição</th>
                                                <th scope="col"></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                for (int i = 0; i < cliente.size(); i++) {
                                                    Cliente categ = cliente.get(i);
                                                    if (categ.getX().equals("A")) {
                                            %>
                                            <tr>
                                                <td><%= categ.getId()%></td>
                                                <td><%= categ.getNome()%></td>
                                                <td><%= categ.getCpf()%></td>
                                                <td><%= categ.getDataNsci()%></td>
                                                <td><%= categ.getEmail()%></td>
                                                <td><%= categ.getDecricao()%></td>
                                                <td>
                                                    <a type="button" href="AcaoVenda?param=SelecionaCliente&id=<%= categ.getId()%>" value="Selecionar" >
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
