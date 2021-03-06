

<%@page import="Entidade.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Horizon - Editar Usuário</title>

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
        
        <script language="JavaScript" src="js/ValidaUsuario.js"></script>
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

                        <form action="DAOUsuario/ListarUsuarios.jsp">
                            <button type="submit" class="btn btn-dark">Voltar</input>
                        </form>

                    </div>
                </div>
            </div>
        </nav>

        <div id="EditarUsuario">

            <form name="AcaoUsuario" id="AcaoUsuario" method="post" action="AcaoUsuario?param=EditarUsuario" onSubmit="return validardados();">

                <% Usuario usu = (Usuario) request.getAttribute("objUsuario");%>

                <center>

                    <input type="hidden" id="id" name="id" value="<%= usu.getId()%>">

                    <div class="form-group col-md-3">
                        <label for="Nome">Nome</label>
                        <input type="text" class="form-control" id="Nome" name="Nome" aria-describedby="Nome" placeholder="Digite seu nome" required="" value="<%= usu.getNome()%>">
                    </div>

                    <div class="form-group col-md-3">
                        <label for="Login">Login</label>
                        <input type="text" class="form-control" id="Login" name="Login" aria-describedby="Login" placeholder="Digite seu login (mínimo 3 caracteres)" required="" value="<%= usu.getLogin()%>">
                    </div>

                    <input type="hidden" id="senha1" name="senha1" value="<%= usu.getSenha()%>">
                    
                    <div class="form-group col-md-3">
                        <label for="Senha">Senha</label>
                        <input type="password" class="form-control" id="Senha" name="Senha" placeholder="Digite sua senha (mínimo 8 caracteres)">
                    </div>

                    <br>
                    <button type="submit" class="btn btn-dark">Atualizar</button>

                </center>
            </form>
        </div>

        <script src="js/bootstrap.bundle.min.js"></script>

    </body>
</html>
