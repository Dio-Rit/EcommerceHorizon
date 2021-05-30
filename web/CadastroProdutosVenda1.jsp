<%-- 
    Document   : CadastroProdutosVenda
    Created on : 16 de mai de 2021, 10:37:35
    Author     : yNot
--%>

<%@page import="DAO.VendaDAO"%>
<%@page import="DAO.VendaProdutoDAO"%>
<%@page import="Entidade.VendaProduto"%>
<%@page import="Apoio.Formatacao"%>
<%@page import="Entidade.Venda"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Horizon - Cadastro de Produtos</title>

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">

        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/navbar.css" rel="stylesheet">
    </head>
    <body>

        <%  VendaDAO h = new VendaDAO();
            Venda l = h.PegaUltimoID();

            Produto usu = (Produto) request.getAttribute("objProduto");
            double x = 0;%>

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
                        <div  class="float-end">
                            <a class="btn btn-dark float-end" href="/EcommerceHorizon/AcaoVenda?param=ExcluirVenda1">Voltar</a>
                        </div>
                    </div>
                </div>
            </div>
        </nav>



        <div id="CadastroVenda">

            <form name="AcaoVenda" id="AcaoVenda" method="post" action="AcaoVenda?param=SalvarProduto"">
                <center>
                    <div class="form-group col-md-3">
                        <label for="Produto">Produto</label>
                        <input type="text" class="form-control" id="Produto" name="Produto" placeholder="" required="" disabled="" value="<%= usu.getNome()%>">
                    </div>
                    <!-- Button trigger modal -->
                    <button type="button" class="btn btn-dark" data-bs-toggle="modal" data-bs-target="#exampleModal">
                        Selecionar
                    </button>
                    <br>
                    <div class="form-group col-md-3">
                        <label for="Quantidade">Quantidade</label>
                        <input type="text" class="form-control" id="Quantidade"  name="Quantidade" data-mask="00000000000000000000">
                    </div>

                    <input type="hidden" id="Produto_id" name="Produto_id" value="<%= usu.getId()%>">

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
                    <div>
                        <center>
                            <button type="submit" class="btn btn-dark">Adicionar Produto</button>
                        </center>
                    </div>

                    <center>
                        </form>
                        <div class="container-fluid">


                            <div class="row">
                                <main class="col-sm-12 ml-sm-auto col-md-12 " role="main">
                                    <div>
                                        <section layout:fragment="content">
                                            <div class="container">
                                                <div class="row">
                                                    <div class="col-sm-10"></div>
                                                </div>

                                                <div class="row">
                                                    <br>
                                                </div>

                                                <%ArrayList<VendaProduto> venda = new VendaProdutoDAO().consultarIdd(l.getId());%> 

                                                <div class="row">
                                                    <table class="table table-striped ">
                                                        <thead>
                                                            <tr>
                                                                <th scope="col">#</th>
                                                                <th scope="col">Quantidade</th>
                                                                <th scope="col">Preço</th>
                                                                <th scope="col">Produto</th>
                                                                <th scope="col"></th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <%
                                                                for (int i = 0; i < venda.size(); i++) {
                                                                    VendaProduto categ = venda.get(i);
                                                                    DAOProduto m = new DAOProduto();
                                                                    Produto c = m.consultarId(categ.getProdutoId());
                                                                    x += categ.getPreco() * categ.getQuantidade();
                                                                    {
                                                            %>
                                                            <tr>
                                                                <td><%= categ.getId()%></td>
                                                                <td><%= categ.getQuantidade()%></td>
                                                                <td><%= categ.getPreco()%></td>
                                                                <td><%= c.getNome()%></td>
                                                                <td>
                                                                    <a href="AcaoVenda?param=ExcluirProdutoVenda&id=<%= categ.getId()%>" method="get" class="btn btn-danger" title="Excluir">
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

                        <div class="form-group col-md-3">
                            <center>
                                <label for="Total">Valor Total</label>
                                <input type="text" class="form-control" id="Total" name="Total" placeholder="" required="" disabled="" value="<%= x%>">
                            </center>
                        </div>
                    </center>
                </center>

                <form name="AcaoVenda" id="AcaoVenda" method="post" action="AcaoVenda?param=FinalizarVenda"">
                    <br>
                    <input type="hidden" id="ValorTotal" name="ValorTotal" value="<%= x%>">
                    <input type="hidden" id="Venda" name="Venda" value="<%= l.getId()%>">
                    <center>
                        <button type="submit" class="btn btn-dark">Finalizar compra</button>
                    </center>
                </form>
                <script src="https://code.jquery.com/jquery-3.6.0.min.js" ></script>
                <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.min.js"></script>
                </body>
                </html>

