/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Apoio.ConexaoBD;
import Apoio.IDAO_T;
import Entidade.Cliente;
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
public class DAOCliente implements IDAO_T<Cliente> {

    ResultSet resultadoQ = null;

    @Override
    public String salvar(Cliente o) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "INSERT INTO cliente VALUES "
                    + "(DEFAULT, "
                    + "'" + o.getNome() + "',"
                    + "'" + o.getCpf() + "',"
                    + "'" + o.getDataNsci() + "',"
                    + "'" + o.getEmail() + "',"
                    + "'" + o.getDecricao() + "',"
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
            System.out.println("Erro salvar cliente = " + e);
            return e.toString();
        }
    }

    @Override
    public String atualizar(Cliente o) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "UPDATE cliente "
                    + "SET nome = '" + o.getNome() + "', "
                    + "cpf = '" + o.getCpf() + "', "
                    + "data_nsci = '" + o.getDataNsci() + "', "
                    + "email = '" + o.getEmail() + "', "
                    + "descricao = '" + o.getDecricao() + "' "
                    + "WHERE id = " + o.getId();

            System.out.println("sql: " + sql);

            int resultado = st.executeUpdate(sql);

            return null;

        } catch (Exception e) {
            System.out.println("Erro ao atualizar cliente = " + e);
            return e.toString();
        }
    }

    @Override
    public String excluir(int id) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "UPDATE cliente "
                    + "SET x = 'Inativo' "
                    + "WHERE id = " + id;

            int resultado = st.executeUpdate(sql);

            return null;

        } catch (Exception e) {
            System.out.println("Erro ao excluir cliente = " + e);
            return e.toString();
        }
    }

    @Override
    public Cliente consultarId(int id) {
        Cliente c = null;

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = ""
                    + "SELECT * "
                    + "FROM cliente "
                    + "WHERE id = " + id;

            System.out.println("Sql: " + sql);

            resultadoQ = st.executeQuery(sql);

            while (resultadoQ.next()) {
                c = new Cliente();

                c.setId(id);
                c.setNome(resultadoQ.getString("nome"));
                c.setCpf(resultadoQ.getString("cpf"));
                c.setDataNsci(resultadoQ.getString("data_nsci"));
                c.setEmail(resultadoQ.getString("email"));
                c.setDecricao(resultadoQ.getString("descricao"));
            }

        } catch (Exception e) {
            System.out.println("Erro consultar cliente = " + e);
        }

        return c;
    }

    public boolean Validacontem(String cpf) {
        try {

            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * "
                    + "FROM cliente "
                    + "WHERE cpf = '" + cpf + "' "
                    + "AND x = 'A';";
            System.out.println("Sql = " + sql);

            resultadoQ = st.executeQuery(sql);

            if (resultadoQ.next()) {
                String newCpf = resultadoQ.getString("cpf");
                System.out.println(newCpf);

                if (cpf.equals(newCpf)) {
                    return false;
                }
            }

        } catch (Exception e) {
            System.out.println("Erro ao procurar cpf = " + e);
        }
        return true;
    }

    public ArrayList<Cliente> consultarTodos() {

        ArrayList<Cliente> cliente = new ArrayList();

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select * "
                    + "from "
                    + "cliente "
                    + "order by nome";
            ResultSet resultado = st.executeQuery(sql);

            while (resultado.next()) {
                Cliente c = new Cliente();
                c.setId(resultado.getInt("id"));
                c.setNome(resultado.getString("nome"));
                c.setCpf(resultado.getString("cpf"));
                c.setDataNsci(resultado.getString("data_nsci"));
                c.setEmail(resultado.getString("email"));
                c.setDecricao(resultado.getString("descricao"));
                c.setX(resultado.getString("x"));

                cliente.add(c);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar Cliente: " + e);
        }

        return cliente;
    }

    public byte[] gerarRelatorio() {
        try {
            Connection conn = ConexaoBD.getInstance().getConnection();

            JasperReport relatorio = JasperCompileManager.compileReport(getClass().getResourceAsStream("/Relatorios/Listagem_clientes.jrxml"));

            Map parameters = new HashMap();

            byte[] bytes = JasperRunManager.runReportToPdf(relatorio, parameters, conn);

            return bytes;
        } catch (Exception e) {
            System.out.println("erro ao gerar relatorio: " + e);
        }
        return null;
    }

}
