/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Apoio.ConexaoBD;
import Apoio.IDAO_T;
import Entidade.Cliente;
import Entidade.Produto;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 *
 * @author yNot
 */
public class DAOProduto implements IDAO_T<Produto> {

    ResultSet resultadoQ = null;

    @Override
    public String salvar(Produto o) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "INSERT INTO produto VALUES "
                    + "(DEFAULT, "
                    + "'" + o.getNome() + "',"
                    + "'" + o.getQuantidade() + "',"
                    + "'" + o.getPreco() + "',"
                    + "'" + o.getDescricao() + "',"
                    + "'" + "A" + "'"
                    + ")";

            System.out.println("Sql: " + sql);

            int resultado = st.executeUpdate(sql);

            if (resultado == 0) {
                return "Erro ao inserir";
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro salvar produto = " + e);
            return e.toString();
        }
    }

    @Override
    public String atualizar(Produto o) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "UPDATE produto "
                    + "SET nome = '" + o.getNome() + "', "
                    + "quantidade = '" + o.getQuantidade() + "', "
                    + "preco = '" + o.getPreco() + "', "
                    + "descricao = '" + o.getDescricao() + "' "
                    + "WHERE id = " + o.getId();

            System.out.println("sql: " + sql);

            int resultado = st.executeUpdate(sql);

            return null;

        } catch (Exception e) {
            System.out.println("Erro ao atualizar produto = " + e);
            return e.toString();
        }
    }

    @Override
    public String excluir(int id) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "UPDATE produto "
                    + "SET x = 'Inativo' "
                    + "WHERE id = " + id;

            int resultado = st.executeUpdate(sql);

            return null;

        } catch (Exception e) {
            System.out.println("Erro ao excluir produto = " + e);
            return e.toString();
        }
    }

    @Override
    public Produto consultarId(int id) {
        Produto p = null;

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = ""
                    + "SELECT * "
                    + "FROM produto "
                    + "WHERE id = " + id;

            System.out.println("Sql: " + sql);

            resultadoQ = st.executeQuery(sql);

            while (resultadoQ.next()) {
                p = new Produto();

                p.setId(id);
                p.setNome(resultadoQ.getString("nome"));
                p.setQuantidade(resultadoQ.getInt("quantidade"));
                p.setPreco(resultadoQ.getDouble("preco"));
                p.setDescricao(resultadoQ.getString("descricao"));
            }

        } catch (Exception e) {
            System.out.println("Erro consultar produto = " + e);
        }

        return p;
    }

    public ArrayList<Produto> consultarTodos() {

        ArrayList<Produto> produto = new ArrayList();

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select * "
                    + "from "
                    + "produto "
                    + "order by nome";
            ResultSet resultado = st.executeQuery(sql);

            while (resultado.next()) {
                Produto c = new Produto();
                c.setId(resultado.getInt("id"));
                c.setNome(resultado.getString("nome"));
                c.setQuantidade(resultado.getInt("quantidade"));
                c.setPreco(resultado.getDouble("preco"));
                c.setDescricao(resultado.getString("descricao"));
                c.setX(resultado.getString("x"));

                produto.add(c);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar Produto: " + e);
        }

        return produto;
    }

    public boolean Validacontem(String nome) {
        try {

            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * "
                    + "FROM produto "
                    + "WHERE nome = '" + nome + "' "
                    + "AND x = 'A';";
            System.out.println("Sql = " + sql);

            resultadoQ = st.executeQuery(sql);

            if (resultadoQ.next()) {
                String newNome = resultadoQ.getString("nome");
                System.out.println(newNome);

                if (nome.equals(newNome)) {
                    return false;
                }
            }

        } catch (Exception e) {
            System.out.println("Erro ao procurar quantidade do produto = " + e);
        }
        return true;
    }

    public void updadeQuantidade(int id, int quantidade) {

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "UPDATE produto "
                    + "SET quantidade = '" + quantidade + "' "
                    + "WHERE id = " + id;

            System.out.println("sql: " + sql);

            int resultado = st.executeUpdate(sql);

        } catch (Exception e) {
            System.out.println("Erro ao atualizar quantidade produto = " + e);
        }

    }

    public byte[] gerarRelatorio() {
        try {
            Connection conn = ConexaoBD.getInstance().getConnection();

            JasperReport relatorio = JasperCompileManager.compileReport(getClass().getResourceAsStream("/Relatorios/Listagem_produto.jrxml"));

            Map parameters = new HashMap();

            byte[] bytes = JasperRunManager.runReportToPdf(relatorio, parameters, conn);

            return bytes;
        } catch (Exception e) {
            System.out.println("erro ao gerar relatorio: " + e);
        }
        return null;
    }

    public ArrayList<Produto> consultar(String nome, String precoInicial, String precoFinal, String quantidade) {

        ArrayList<Produto> produtos = new ArrayList();

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * \n"
                    + "	FROM produto\n"
                    + " WHERE ";

            if (precoFinal != "") {

                sql += "preco >=" + precoInicial + "  AND preco <=" + precoFinal + "\n";
            }
            if (precoFinal == "") {
                precoFinal = "999999999999999999999999999999999";
                sql += "preco >=" + precoInicial + "  AND preco <=" + precoFinal + "\n";
            }

            if (quantidade.length() > 0) {
                sql += " AND quantidade ILIKE '%" + quantidade + "%'\n";
            }

            if (nome.length() > 0) {
                sql += " AND nome ILIKE '%" + nome + "%'";
            }

            sql += " AND x = 'A'";
            sql += " ORDER BY nome";

            System.out.println("SQL: " + sql);

            resultadoQ = st.executeQuery(sql);

            Produto produto;
            while (resultadoQ.next()) {
                produto = new Produto();

                produto.setId(resultadoQ.getInt("id"));
                produto.setNome(resultadoQ.getString("nome"));
                produto.setQuantidade(Integer.parseInt(resultadoQ.getString("quantidade")));
                produto.setPreco(Double.parseDouble(resultadoQ.getString("preco")));
                produto.setDescricao(resultadoQ.getString("descricao"));
                produto.setX(resultadoQ.getString("x"));
                produtos.add(produto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("produtos.l: " + produtos.size());
        return produtos;
    }
}
