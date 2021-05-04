/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Apoio.ConexaoBD;
import Apoio.IDAO_T;
import Entidade.Fornecedor;
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
public class DAOFornecedor implements IDAO_T<Fornecedor> {

    ResultSet resultadoQ = null;

    @Override
    public String salvar(Fornecedor o) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "INSERT INTO fornecedor VALUES "
                    + "(DEFAULT, "
                    + "'" + o.getNome() + "',"
                    + "'" + o.getCnpj() + "',"
                    + "'" + o.getTelefone() + "',"
                    + "'" + o.getEmail() + "',"
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
            System.out.println("Erro salvar fornecedor = " + e);
            return e.toString();
        }
    }

    @Override
    public String atualizar(Fornecedor o) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "UPDATE fornecedor "
                    + "SET nome = '" + o.getNome() + "', "
                    + "cnpj = '" + o.getCnpj() + "', "
                    + "telefone = '" + o.getTelefone() + "', "
                    + "email = '" + o.getEmail() + "' "
                    + "WHERE id = " + o.getId();

            System.out.println("sql: " + sql);

            int resultado = st.executeUpdate(sql);

            return null;

        } catch (Exception e) {
            System.out.println("Erro ao atualizar fornecedor = " + e);
            return e.toString();
        }
    }

    @Override
    public String excluir(int id) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "UPDATE fornecedor "
                    + "SET x = 'Inativo' "
                    + "WHERE id = " + id;

            int resultado = st.executeUpdate(sql);

            return null;

        } catch (Exception e) {
            System.out.println("Erro ao excluir fornecedor = " + e);
            return e.toString();
        }
    }

    @Override
    public Fornecedor consultarId(int id) {
        Fornecedor f = null;

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = ""
                    + "SELECT * "
                    + "FROM fornecedor "
                    + "WHERE id = " + id;

            System.out.println("Sql: " + sql);

            resultadoQ = st.executeQuery(sql);

            while (resultadoQ.next()) {
                f = new Fornecedor();

                f.setId(id);
                f.setNome(resultadoQ.getString("nome"));
                f.setCnpj(resultadoQ.getString("cnpj"));
                f.setTelefone(resultadoQ.getString("telefone"));
                f.setEmail(resultadoQ.getString("email"));
            }

        } catch (Exception e) {
            System.out.println("Erro consultar fornecedor = " + e);
        }

        return f;
    }

    public boolean Validacontem(String cnpj) {
        try {

            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * "
                    + "FROM fornecedor "
                    + "WHERE cnpj = '" + cnpj + "' "
                    + "AND x = 'A';";
            System.out.println("Sql = " + sql);

            resultadoQ = st.executeQuery(sql);

            if (resultadoQ.next()) {
                String newCnpj = resultadoQ.getString("cnpj");
                System.out.println(newCnpj);

                if (cnpj.equals(newCnpj)) {
                    return false;
                }
            }

        } catch (Exception e) {
            System.out.println("Erro ao procurar cnpj = " + e);
        }
        return true;
    }
    
    public ArrayList<Fornecedor> consultarTodos() {

        ArrayList<Fornecedor> fornecedor = new ArrayList();

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select * "
                    + "from "
                    + "fornecedor "
                    + "order by nome";
            ResultSet resultado = st.executeQuery(sql);

            while (resultado.next()) {
                Fornecedor c = new Fornecedor();
                c.setId(resultado.getInt("id"));
                c.setNome(resultado.getString("nome")); 
                c.setCnpj(resultado.getString("cnpj"));
                c.setTelefone(resultado.getString("telefone"));
                c.setEmail(resultado.getString("email"));
                c.setX(resultado.getString("x"));

                fornecedor.add(c);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar Fornecedor: " + e);
        }

        return fornecedor;
    }
    
        public byte[] gerarRelatorio() {
        try {
            Connection conn = ConexaoBD.getInstance().getConnection();

            JasperReport relatorio = JasperCompileManager.compileReport(getClass().getResourceAsStream("/Relatorios/Listagem_fornecedor.jrxml"));

            Map parameters = new HashMap();

            byte[] bytes = JasperRunManager.runReportToPdf(relatorio, parameters, conn);

            return bytes;
        } catch (Exception e) {
            System.out.println("erro ao gerar relatorio: " + e);
        }
        return null;
    }
}
