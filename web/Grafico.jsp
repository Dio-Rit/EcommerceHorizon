<%-- 
    Document   : Grafico
    Created on : 30 de mai de 2021, 10:24:48
    Author     : yNot
--%>

<%@page import="DAO.VendaDAO"%>
<%@page import="Entidade.ListaVenda"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gráfico</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <link href="css/navbar.css" rel="stylesheet">


        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
        <style>
            .bloco {                
                padding: 0 200px 200px 200px;
            }
        </style>


        <script>
            google.charts.load('current', {packages: ['corechart', 'bar']});
            google.charts.setOnLoadCallback(GraficoVendas);

            function GraficoVendas() {

            <%
                VendaDAO x = new VendaDAO();
                ArrayList<ListaVenda> venda = x.consultarTodosGrafico();

                String Pontos = "[['Cliente', 'Valor total da Venda'],";

                for (int i = 0; i < venda.size(); i++) {
                    ListaVenda categ = venda.get(i);
                    Pontos += "['" + categ.getCliente() + "', " + categ.getValorTotal() + "],\n";
                }

                Pontos += "]";

                System.out.println(Pontos);
            %>

                var data = google.visualization.arrayToDataTable(<%= Pontos%>);


                var options = {
                    title: 'Gráfico de Vendas Realizadas',
                    chartArea: {width: '50%'},
                    hAxis: {
                        title: '',
                        minValue: 0
                    },
                    vAxis: {
                        title: ''
                    }
                };

                var chart = new google.visualization.BarChart(document.getElementById('GraficoVenda'));
                chart.draw(data, options);
            }
        </script>
        
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

                        <form action="home.jsp">
                            <button type="submit" class="btn btn-dark">Voltar</input>
                        </form>

                    </div>
                </div>
            </div>
        </nav>


        <div class="bloco">
            <div id="GraficoVenda" style="height: 500px"></div>
        </div>

        <script src="js/bootstrap.bundle.min.js"></script>
    </body>
</html>
