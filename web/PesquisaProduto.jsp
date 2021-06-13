<%-- 
    Document   : PesquisaProduto
    Created on : 8 de jun de 2021, 18:46:59
    Author     : yNot
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pesquisar Produtos</title>

        <script type="text/javascript" src="js/funcaoAjax.js"></script>

        <link href="css/bootstrap.min.css" rel="stylesheet">

        <link href="css/navbar.css" rel="stylesheet">
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

                        <form action="home.jsp">
                            <button type="submit" class="btn btn-dark">Voltar</input>
                        </form>

                    </div>
                </div>
            </div>
        </nav>
    <center>
        <form name="formulario" method="post" action="javascript:carregaPaginaProdutos('ListarProdutoAjax.jsp');">

            <input style="width: 30%" type="text" id="descricao" name="nome" placeholder="Digite o nome do produto">

            <br>
            <br>

            <input style="width: 30%" type="text" id="codigoBarras" name="precoInicial" placeholder="Digite o preço inicial">

            <br>
            <br>
            
            <input style="width: 30%" type="text" id="codigoBarras" name="precoFinal" placeholder="Digite o preço final">

            <br>
            <br>

            <input style="width: 30%" type="text" id="codigoBarras" name="quantidade" placeholder="Digite a quantidade">

            <br>
            <br>

            <input type="submit" class="btn btn-dark" value="Pesquisar">

        </form>

        <br>


        <div id="retornoAjax">

        </div>
    </center>
    <script src="js/bootstrap.bundle.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" ></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.min.js"></script>
</body>
</html>