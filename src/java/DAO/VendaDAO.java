/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Apoio.ConexaoBD;
import Apoio.Formatacao;
import Apoio.IDAO_T;
import Entidade.Produto;
import Entidade.Venda;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 *
 * @author PC
 */
public class VendaDAO implements IDAO_T<Venda> {

    int k = 0;
    ArrayList<Produto> ArrayProduto = new ArrayList<>();
    ArrayList<Produto> ArrayProdutoVenda = new ArrayList<>();

    private static int lancamento_id = 0;

    public int obterLancamentoId() {
        return lancamento_id;
    }

    ResultSet resultadoQ = null;

    @Override
    public String salvar(Venda o) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "INSERT INTO venda VALUES "
                    + "(DEFAULT, "
                    + "'" + o.getData() + "',"
                    + "'" + o.getValorTotal() + "',"
                    + "'" + "A" + "',"
                    + "'" + o.getClienteId() + "'"
                    + ")";

            System.out.println("Sql: " + sql);

            int resultado = st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet chave = st.getGeneratedKeys();

            if (chave.next()) {
                lancamento_id = chave.getInt(1);
                System.out.println("Id gerado: " + lancamento_id);
            }

            if (resultado == 0) {
                return "Erro ao inserir";
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro salvar venda = " + e);
            return e.toString();
        }
    }

    @Override
    public String atualizar(Venda o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String excluir(int id) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "DELETE from venda "
                    + "WHERE id = " + id;

            int resultado = st.executeUpdate(sql);

            return null;

        } catch (Exception e) {
            System.out.println("Erro ao excluir venda = " + e);
            return e.toString();
        }
    }

    @Override
    public Venda consultarId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void uptadeTotal(int i, double d) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "UPDATE venda "
                    + "SET valor_total = '" + d + "' "
                    + "WHERE id = " + i;

            System.out.println("sql: " + sql);

            int resultado = st.executeUpdate(sql);

        } catch (Exception e) {
            System.out.println("Erro ao atualizar total da venda= " + e);
        }
    }

    public boolean AumentaProduto(int id) {

        try {

            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * "
                    + "FROM venda_produto "
                    + "WHERE venda_id = '" + id + "' "
                    + "ORDER BY produto_id;";

            resultadoQ = st.executeQuery(sql);

            while (resultadoQ.next()) {

                Produto p = new Produto();

                p.setId(resultadoQ.getInt("produto_id"));
                p.setQuantidade(resultadoQ.getInt("quantidade"));

                ArrayProdutoVenda.add(p);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro save ArrayProdutoVenda : " + e);
        }

        try {

            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * "
                    + "FROM produto "
                    + "WHERE x = 'A' "
                    + "ORDER BY id;";

            resultadoQ = st.executeQuery(sql);

            while (resultadoQ.next()) {

                Produto p = new Produto();

                p.setId(resultadoQ.getInt("id"));
                p.setQuantidade(resultadoQ.getInt("quantidade"));

                ArrayProduto.add(p);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro save ArrayProduto : " + e);
        }

        for (int i = 0; k < ArrayProdutoVenda.size(); i++) {

            if (ArrayProduto.get(i).getId() == ArrayProdutoVenda.get(k).getId()) {

                int quan = ArrayProduto.get(i).getQuantidade() + ArrayProdutoVenda.get(k).getQuantidade();
                new DAOProduto().updadeQuantidade(ArrayProduto.get(i).getId(), quan);
                k += 1;

            } else {
                System.out.println("Produtos diferentes");

            }

        }

        return true;
    }

    public boolean DiminuiProduto(int id) {

        try {

            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * "
                    + "FROM venda_produto "
                    + "WHERE venda_id = '" + id + "' "
                    + "ORDER BY produto_id;";

            resultadoQ = st.executeQuery(sql);

            while (resultadoQ.next()) {

                Produto p = new Produto();

                p.setId(resultadoQ.getInt("produto_id"));
                p.setQuantidade(resultadoQ.getInt("quantidade"));

                ArrayProdutoVenda.add(p);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro save ArrayProdutoVenda : " + e);
        }

        try {

            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * "
                    + "FROM produto "
                    + "WHERE x = 'A' "
                    + "ORDER BY id;";

            resultadoQ = st.executeQuery(sql);

            while (resultadoQ.next()) {

                Produto p = new Produto();

                p.setId(resultadoQ.getInt("id"));
                p.setQuantidade(resultadoQ.getInt("quantidade"));

                ArrayProduto.add(p);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro save ArrayProduto : " + e);
        }

        for (int i = 0; k < ArrayProdutoVenda.size(); i++) {

            if (ArrayProduto.get(i).getId() == ArrayProdutoVenda.get(k).getId()) {

                int quan = ArrayProduto.get(i).getQuantidade() - ArrayProdutoVenda.get(k).getQuantidade();
                new DAOProduto().updadeQuantidade(ArrayProduto.get(i).getId(), quan);
                k += 1;

            } else {
                System.out.println("Produtos diferentes");

            }

        }

        return true;
    }

    public byte[] gerarRelatorio(String Ini, String Fini) {
        try {
            Connection conn = ConexaoBD.getInstance().getConnection();

            JasperReport relatorio = JasperCompileManager.compileReport(getClass().getResourceAsStream("/Relatorios/Listagem_compra.jrxml"));

            Map parameters = new HashMap();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            URL teste = getClass().getResource("/Relatorios/Relatorio_compra_sub.jasper");
            parameters.put("data_inicial", sdf.parse(Ini));
            parameters.put("data_final", sdf.parse(Fini));
            parameters.put("SUBREPORT_DIR", teste.toString().replaceAll("file:/", ""));

            byte[] bytes = JasperRunManager.runReportToPdf(relatorio, parameters, conn);

            return bytes;
        } catch (Exception e) {
            System.out.println("erro ao gerar relatorio: " + e);
        }
        return null;
    }

}