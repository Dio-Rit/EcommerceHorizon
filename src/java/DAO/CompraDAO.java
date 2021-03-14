/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Apoio.ConexaoBD;
import Apoio.Formatacao;
import Apoio.IDAO_T;
import Entidade.Compra;
import Entidade.Produto;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author PC
 */
public class CompraDAO implements IDAO_T<Compra> {

    int k = 0;
    ArrayList<Produto> ArrayProduto = new ArrayList<>();
    ArrayList<Produto> ArrayProdutoCompra = new ArrayList<>();

    private static int lancamento_id = 0;

    public int obterLancamentoId() {
        return lancamento_id;
    }

    ResultSet resultadoQ = null;

    @Override
    public String salvar(Compra o) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "INSERT INTO compra VALUES "
                    + "(DEFAULT, "
                    + "'" + o.getData() + "',"
                    + "'" + o.getValorTotal() + "',"
                    + "'" + "A" + "',"
                    + "'" + o.getFornecedorId() + "'"
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
    public String atualizar(Compra o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String excluir(int id) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "DELETE from compra "
                    + "WHERE id = " + id;

            int resultado = st.executeUpdate(sql);

            return null;

        } catch (Exception e) {
            System.out.println("Erro ao excluir compra = " + e);
            return e.toString();
        }
    }

    @Override
    public Compra consultarId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void uptadeTotal(int i, double d) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "UPDATE compra "
                    + "SET valor_total = '" + d + "' "
                    + "WHERE id = " + i;

            System.out.println("sql: " + sql);

            int resultado = st.executeUpdate(sql);

        } catch (Exception e) {
            System.out.println("Erro ao atualizar total da compra= " + e);
        }
    }

    public void popularTabela(JTable tabela, String criterio) {
        // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[4];
        cabecalho[0] = "Código";
        cabecalho[1] = "Fornecedor";
        cabecalho[2] = "Data";
        cabecalho[3] = "Valor Total";

        // cria matriz de acordo com nº de registros da tabela
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT count(*) FROM compra c, fornecedor f  WHERE f.nome ILIKE '%" + criterio + "%' and c.x = 'A'" + "and c.fornecedor_id = f.id");

            resultadoQ.next();

            dadosTabela = new Object[resultadoQ.getInt(1)][4];

        } catch (Exception e) {
            System.out.println("Erro ao consultar compra: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT * FROM compra c, fornecedor f "
                    + "WHERE "
                    + "f.nome ILIKE '%" + criterio + "%' "
                    + "and c.x = 'A'"
                    + "and c.fornecedor_id = f.id "
                    + "ORDER BY c.id desc");

            while (resultadoQ.next()) {
                String Data = Formatacao.ajustaDataDMA(resultadoQ.getString("data"));
                dadosTabela[lin][0] = resultadoQ.getInt("id");
                dadosTabela[lin][1] = resultadoQ.getString("nome");
                dadosTabela[lin][2] = Data;
                dadosTabela[lin][3] = resultadoQ.getString("valor_total");

                // caso a coluna precise exibir uma imagem
//                if (resultadoQ.getBoolean("Situacao")) {
//                    dadosTabela[lin][2] = new ImageIcon(getClass().getClassLoader().getResource("Interface/imagens/status_ativo.png"));
//                } else {
//                    dadosTabela[lin][2] = new ImageIcon(getClass().getClassLoader().getResource("Interface/imagens/status_inativo.png"));
//                }
                lin++;
            }
        } catch (Exception e) {
            System.out.println("problemas para popular tabela compra");
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

    public boolean AumentaProduto(int id) {

        try {

            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * "
                    + "FROM compra_produto "
                    + "WHERE compra_id = '" + id + "' "
                    + "ORDER BY produto_id;";

            resultadoQ = st.executeQuery(sql);

            while (resultadoQ.next()) {

                Produto p = new Produto();

                p.setId(resultadoQ.getInt("produto_id"));
                p.setQuantidade(resultadoQ.getInt("quantidade"));

                ArrayProdutoCompra.add(p);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro save ArrayProdutoCompra : " + e);
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

        for (int i = 0; k < ArrayProdutoCompra.size(); i++) {
             

            if (ArrayProduto.get(i).getId() == ArrayProdutoCompra.get(k).getId()) {

                int quan = ArrayProduto.get(i).getQuantidade() + ArrayProdutoCompra.get(k).getQuantidade();
                new ProdutoDAO().updadeQuantidade(ArrayProduto.get(i).getId(), quan);
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
                    + "FROM compra_produto "
                    + "WHERE compra_id = '" + id + "' "
                    + "ORDER BY produto_id;";

            resultadoQ = st.executeQuery(sql);

            while (resultadoQ.next()) {

                Produto p = new Produto();

                p.setId(resultadoQ.getInt("produto_id"));
                p.setQuantidade(resultadoQ.getInt("quantidade"));

                ArrayProdutoCompra.add(p);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro save ArrayProdutoCompra : " + e);
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

        for (int i = 0; k < ArrayProdutoCompra.size(); i++) {

            if (ArrayProduto.get(i).getId() == ArrayProdutoCompra.get(k).getId()) {

                int quan = ArrayProduto.get(i).getQuantidade() - ArrayProdutoCompra.get(k).getQuantidade();
                new ProdutoDAO().updadeQuantidade(ArrayProduto.get(i).getId(), quan);
                k += 1;

            } else {
                System.out.println("Produtos diferentes");

            }

        }

        return true;
    }

}
