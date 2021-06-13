<%-- 
    Document   : ListarClienteAjax
    Created on : 8 de jun de 2021, 19:17:36
    Author     : yNot
--%>

<%@page import="DAO.DAOCliente"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Entidade.Cliente"%>
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
        <h1>Listagem de Clientes</h1>
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
                                    String cpf = request.getParameter("cpf");
                                    String dataInicial = request.getParameter("dataInicial");
                                    String dataFinal = request.getParameter("dataFinal");
                                    
                                    if(dataInicial == ""){
                                        dataInicial = "1/1/1000";
                                    }
                                    ArrayList<Cliente> cliente = new DAOCliente().consultar(nome, cpf, dataInicial, dataFinal);
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
