/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Apoio.ConexaoBD;
import Apoio.IDAO_T;
import Entidade.VendaProduto;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class VendaProdutoDAO implements IDAO_T<VendaProduto> {

    ResultSet resultadoQ = null;

    @Override
    public String salvar(VendaProduto o) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "INSERT INTO venda_produto VALUES "
                    + "(DEFAULT, "
                    + "'" + o.getQuantidade() + "',"
                    + "'" + o.getPreco() + "',"
                    + "'" + o.getProdutoId() + "',"
                    + "'" + o.getVendaId() + "',"
                    + "'" + o.getVendaClienteId() + "'"
                    + ")";

            System.out.println("Sql: " + sql);

            int resultado = st.executeUpdate(sql);

            if (resultado == 0) {
                return "Erro ao inserir";
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro salvar produtos da venda = " + e);
            return e.toString();
        }
    }

    @Override
    public String atualizar(VendaProduto o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String excluir(int id) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "DELETE FROM venda_produto "
                    + "WHERE id = " + id;

            int resultado = st.executeUpdate(sql);

            return null;

        } catch (Exception e) {
            System.out.println("Erro ao excluir produto da venda = " + e);
            return e.toString();
        }
    }

    public ArrayList<VendaProduto> consultarIdd(int id) {
        ArrayList<VendaProduto> vp = new ArrayList(); 

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = ""
                    + "SELECT * "
                    + "FROM venda_produto "
                    + "WHERE venda_id = " + id
                    + "order by id desc";

            ResultSet resultado = st.executeQuery(sql);

            while (resultadoQ.next()) {
                VendaProduto c = new VendaProduto();
                c.setId(resultado.getInt("id"));
                c.setQuantidade(resultado.getInt("quantidade"));
                c.setPreco(resultado.getDouble("preco"));
                c.setProdutoId(resultado.getInt("produto_id"));
                c.setVendaId(resultado.getInt("venda_id"));
                c.setVendaClienteId(resultado.getInt("venda_cliente_id")); 
                
                vp.add(c);
            }

        } catch (Exception e) {
            System.out.println("Erro consultar Produtos da venda = " + e);
        }

        return vp;
    }
    

    public String cancelaVenda(int id){
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "DELETE from venda_produto "
                    + "WHERE venda_id = " + id;

            int resultado = st.executeUpdate(sql);

            return null;

        } catch (Exception e) {
            System.out.println("Erro ao excluir venda = " + e);
            return e.toString();
        }
    }

    @Override
    public VendaProduto consultarId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
