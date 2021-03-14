/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Apoio.ConexaoBD;
import Apoio.IDAO_T;
import Entidade.Produto;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author dionatan.ritter
 */
public class ProdutoDAO implements IDAO_T<Produto> {

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

    public void popularTabela(JTable tabela, String criterio) {
        // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[5];
        cabecalho[0] = "Código";
        cabecalho[1] = "Nome";
        cabecalho[2] = "Quantidade";
        cabecalho[3] = "Preço";
        cabecalho[4] = "Descrição";

        // cria matriz de acordo com nº de registros da tabela
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT count(*) FROM produto WHERE nome ILIKE '%" + criterio + "%' and x = 'A'");

            resultadoQ.next();

            dadosTabela = new Object[resultadoQ.getInt(1)][5];

        } catch (Exception e) {
            System.out.println("Erro ao consultar produto: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT * FROM produto "
                    + "WHERE "
                    + "nome ILIKE '%" + criterio + "%' "
                    + "and x = 'A'"
                    + "ORDER BY nome");

            while (resultadoQ.next()) {

                dadosTabela[lin][0] = resultadoQ.getInt("id");
                dadosTabela[lin][1] = resultadoQ.getString("nome");
                dadosTabela[lin][2] = resultadoQ.getString("quantidade");
                dadosTabela[lin][3] = resultadoQ.getString("preco");
                dadosTabela[lin][4] = resultadoQ.getString("descricao");

                // caso a coluna precise exibir uma imagem
//                if (resultadoQ.getBoolean("Situacao")) {
//                    dadosTabela[lin][2] = new ImageIcon(getClass().getClassLoader().getResource("Interface/imagens/status_ativo.png"));
//                } else {
//                    dadosTabela[lin][2] = new ImageIcon(getClass().getClassLoader().getResource("Interface/imagens/status_inativo.png"));
//                }
                lin++;
            }
        } catch (Exception e) {
            System.out.println("problemas para popular tabela produto");
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
