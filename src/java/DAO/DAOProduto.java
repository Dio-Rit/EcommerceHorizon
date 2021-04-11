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
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


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


    public boolean Validacontem(int q, int id) {
        try {

            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * "
                    + "FROM produto "
                    + "WHERE id = '" + id + "' "
                    + "AND x = 'A';";
            System.out.println("Sql = " + sql);

            resultadoQ = st.executeQuery(sql);

            if (resultadoQ.next()) {
                int newQuantidade = resultadoQ.getInt("quantidade");
                System.out.println(newQuantidade);

                if (q > newQuantidade) {
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
    
}
