

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

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
    </head>
    <body>
        <nav class="navbar navbar-expand-md navbar-dark bg-dark" aria-label="Fourth navbar example">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">Controle de Despesas</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarsExample04" aria-controls="navbarsExample04" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="float-end" id="navbarsExample04">
                    <div class="float-end" id="navbarsExampleDefault">
                        <ul class="navbar-nav mr-auto">
                        </ul>

                        <form action="../DAOUsuario/ListarUsuarios.jsp">
                            <button type="submit" class="btn btn-dark">Voltar</input>
                        </form>

                    </div>
                </div>
            </div>
        </nav>

        <div id="CadastroUsuario">
            <form name="AcaoUsuario" id="AcaoUsuario" method="post" action="../AcaoUsuario?param=SalvarUsuario">
                <center>
                    <div class="form-group col-md-3">
                        <label for="Nome">Nome</label>
                        <input type="text" class="form-control" id="Nome" name="Nome" aria-describedby="Nome" placeholder="Digite seu nome" required="">
                    </div>

                    <div class="form-group col-md-3">
                        <label for="Login">Login</label>
                        <input type="text" class="form-control" id="Login" name="Login" aria-describedby="Login" placeholder="Digite seu login" required="">
                    </div>

                    <div class="form-group col-md-3">
                        <label for="Senha">Senha</label>
                        <input type="password" class="form-control" id="Senha" name="Senha" placeholder="Digite sua senha" required="">
                    </div>
                    <br>
                    <button type="submit" class="btn btn-dark">Cadastrar</button>
                </center>
            </form>
        </div>



        <script src="../js/bootstrap.bundle.min.js"></script>

    </body>
</html>
