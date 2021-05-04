

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Horizon - Home</title>

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

        <link href="css/BgHome.css" rel="stylesheet">

    </head>
    <body class="bg">
        <nav class="navbar navbar-expand-md navbar-dark bg-dark" aria-label="Fourth navbar example">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">Horizon</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarsExample04" aria-controls="navbarsExample04" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarsExample04">
                    <ul class="navbar-nav me-auto mb-2 mb-md-0">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="dropdown04" data-bs-toggle="dropdown" aria-expanded="false">Cadastrar</a>
                            <ul class="dropdown-menu" aria-labelledby="dropdown04">
                                <li><a class="dropdown-item" href="DAOCliente/ListarCliente.jsp">Cliente</a></li>
                                <li><a class="dropdown-item" href="DAOFornecedor/ListarFornecedor.jsp">Fornecedor</a></li>
                                <li><a class="dropdown-item" href="DAOProduto/ListarProduto.jsp">Produto</a></li>
                                <li><a class="dropdown-item" href="DAOUsuario/ListarUsuarios.jsp">Usuário</a></li>
                            </ul>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="dropdown04" data-bs-toggle="dropdown" aria-expanded="false">Vendas</a>
                            <ul class="dropdown-menu" aria-labelledby="dropdown04">
                                <li><a class="dropdown-item" href="">Vender</a></li>
                                <li><a class="dropdown-item" href="">Listar Vendas</a></li>
                            </ul>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="dropdown04" data-bs-toggle="dropdown" aria-expanded="false">Compras</a>
                            <ul class="dropdown-menu" aria-labelledby="dropdown04">
                                <li><a class="dropdown-item" href="">Comprar</a></li>
                                <li><a class="dropdown-item" href="">Listar compras</a></li>
                            </ul>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="dropdown04" data-bs-toggle="dropdown" aria-expanded="false">Listagem</a>
                            <ul class="dropdown-menu" aria-labelledby="dropdown04">
                                <li><a class="dropdown-item" href="Relatorios/RelatorioCliente.jsp" target="_blank">Clientes</a></li>
                                <li><a class="dropdown-item" href="Relatorios/RelatorioFornecedor.jsp" target="_blank">Fornecedores</a></li>
                                <li><a class="dropdown-item" href="Relatorios/RelatorioProduto.jsp" target="_blank">Produtos</a></li>
                            </ul>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="dropdown04" data-bs-toggle="dropdown" aria-expanded="false">Relatórios</a>
                            <ul class="dropdown-menu" aria-labelledby="dropdown04">
                                <li><a class="dropdown-item" href="Relatorios/RelatorioCliente.jsp" target="_blank">Vendas</a></li>
                                <li><a class="dropdown-item" href="Relatorios/RelatorioFornecedor.jsp" target="_blank">Compras</a></li>
                            </ul>
                        </li>
                    </ul>
                    <div class="float-end" id="navbarsExampleDefault">
                        <form action="Acaologin?param=Logout">
                            <a class="btn btn-dark" href="Acaologin?param=logout">Sair</a>
                        </form> 
                    </div>

                </div>
            </div>
        </nav>

        <script src="js/bootstrap.bundle.min.js"></script>

    </body>
</html>
