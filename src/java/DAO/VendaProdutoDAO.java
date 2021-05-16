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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

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

    @Override
    public VendaProduto consultarId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void popularTabela(JTable tabela, String criterio) {
        // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[4];
        cabecalho[0] = "Código";
        cabecalho[1] = "Produto";
        cabecalho[2] = "Preço";
        cabecalho[3] = "Quantidade";

        // cria matriz de acordo com nº de registros da tabela
        try {
            String sql
                    = "SELECT count(*) "
                    + "FROM venda_produto v, produto p "
                    + "WHERE "
                    + "v.produto_id = p.id AND "
                    + "v.venda_id = " + Integer.parseInt(criterio) + "";

            System.out.println("SQL consulta: " + sql);

            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            resultadoQ.next();

            dadosTabela = new Object[resultadoQ.getInt(1)][4];

        } catch (Exception e) {
            System.out.println("Erro ao consultar venda_produto: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {

            String sql
                    = "SELECT x.id, p.nome, p.preco, x.quantidade "
                    + "FROM venda_produto x, produto p "
                    + "WHERE "
                    + "x.produto_id = p.id AND "
                    + "x.venda_id = " + Integer.parseInt(criterio) + " "
                    + "ORDER BY nome";

            System.out.println("SQL consulta: " + sql + "\n");

            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            while (resultadoQ.next()) {

                dadosTabela[lin][0] = resultadoQ.getInt("id");
                dadosTabela[lin][1] = resultadoQ.getString("nome");
                dadosTabela[lin][2] = resultadoQ.getString("preco");
                dadosTabela[lin][3] = resultadoQ.getString("quantidade");

                // caso a coluna precise exibir uma imagem
//                if (resultadoQ.getBoolean("Situacao")) {
//                    dadosTabela[lin][2] = new ImageIcon(getClass().getClassLoader().getResource("Interface/imagens/status_ativo.png"));
//                } else {
//                    dadosTabela[lin][2] = new ImageIcon(getClass().getClassLoader().getResource("Interface/imagens/status_inativo.png"));
//                }
                lin++;
            }
        } catch (Exception e) {
            System.out.println("problemas para popular tabela cliente");
            System.out.println(e);
        }

        // configuracoes adicionais no componente tabela
        tabela.setModel(new DefaultTableModel(dadosTabela, cabecalho) {
            @Override
            // quando retorno for FALSE, a tabela nao é editavel
            public boolean isCellEditable(int row, int column) {
                return false;
                /*  
                 if (column == 3) {  // apenas a coluna 3 sera editavel
                 return true;
                 } else {
                 return false;
                 }
                 */
            }

            // alteracao no metodo que determina a coluna em que o objeto ImageIcon devera aparecer
            @Override
            public Class getColumnClass(int column) {

                if (column == 2) {
//                    return ImageIcon.class;
                }
                return Object.class;
            }
        });

        // permite seleção de apenas uma linha da tabela
        tabela.setSelectionMode(0);

        // redimensiona as colunas de uma tabela
        TableColumn column = null;
        for (int i = 0; i < tabela.getColumnCount(); i++) {
            column = tabela.getColumnModel().getColumn(i);
            switch (i) {
                case 0:
                    column.setPreferredWidth(17);
                    break;
                case 1:
                    column.setPreferredWidth(140);
                    break;
//                case 2:
//                    column.setPreferredWidth(14);
//                    break;
            }
        }
        // renderizacao das linhas da tabela = mudar a cor
//        jTable1.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
//
//            @Override
//            public Component getTableCellRendererComponent(JTable table, Object value,
//                    boolean isSelected, boolean hasFocus, int row, int column) {
//                super.getTableCellRendererComponent(table, value, isSelected,
//                        hasFocus, row, column);
//                if (row % 2 == 0) {
//                    setBackground(Color.GREEN);
//                } else {
//                    setBackground(Color.LIGHT_GRAY);
//                }
//                return this;
//            }
//        });
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


}
