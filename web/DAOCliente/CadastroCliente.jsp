<%-- 
    Document   : CadastroCliente
    Created on : 11 de abr de 2021, 14:05:13
    Author     : yNot
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Horizon - Cadastro Cliente</title>

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
        <link href="../css/bootstrap.min.css" rel="stylesheet">

        <link href="../css/navbar.css" rel="stylesheet">

        <script language="JavaScript" src="../js/ValidaCliente.js"></script>

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

                        <form action="../DAOCliente/ListarCliente.jsp">
                            <button type="submit" class="btn btn-dark">Voltar</input>
                        </form>

                    </div>
                </div>
            </div>
        </nav>

        <div id="CadastroCliente">
            <form name="AcaoCliente" id="AcaoCliente" method="post" action="../AcaoCliente?param=SalvarCliente" onSubmit="return validardadosCliente();">
                <center>
                    <div class="form-group col-md-3">
                        <label for="Nome">Nome</label>
                        <input type="text" class="form-control" id="nome" name="nome" aria-describedby="Nome" placeholder="Digite seu nome" required="" data-mask="SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS">
                    </div>

                    <div class="form-group col-md-3">
                        <label for="CPF">CPF</label>
                        <input type="text" class="form-control" id="cpf" name="cpf" aria-describedby="cpf" placeholder="000.000.00-00" required="" data-mask="000.000.000-00">
                    </div>

                    <div class="form-group col-md-3">
                        <label for="Data_nsci">Data Nascimento</label>
                        <input type="text" class="form-control" id="data_nsci" name="data_nsci" placeholder="00/00/0000" required="" data-mask="00/00/0000">
                    </div>

                    <div class="form-group col-md-3">
                        <label for="Email">Email</label>
                        <input type="email" class="form-control" id="email" name="email" placeholder="Digite seu email" required="">
                    </div>

                    <div class="form-group col-md-3">
                        <label for="Descricao">Descrição</label>
                        <input type="text" class="form-control" id="descricao" name="descricao" placeholder="Digite uma descrição" required="">
                    </div>

                    <br>
                    <button type="submit" class="btn btn-dark">Cadastrar</button>
                </center>
            </form>
        </div>

        <script src="../js/bootstrap.bundle.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js" ></script>"
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.min.js"></script>

    </body>
</html>
