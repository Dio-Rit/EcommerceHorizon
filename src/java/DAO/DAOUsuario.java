/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Apoio.ConexaoBD;
import Apoio.IDAO_T;
import Entidade.Usuario;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author yNot
 */
public class DAOUsuario implements IDAO_T<Usuario> {

    ResultSet resultadoQ = null;

    public static String MD5(String s) throws NoSuchAlgorithmException {
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(s.getBytes(), 0, s.length());
        return new BigInteger(1, m.digest()).toString(16);
    }

    public boolean logar(String login, String senha) {
        try {

            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * "
                    + "FROM usuario "
                    + "WHERE login = '" + login + "' "
                    + "AND senha = '" + senha + "' "
                    + "AND x = 'A';";
            System.out.println("Sql = " + sql);

            resultadoQ = st.executeQuery(sql);

            if (resultadoQ.next()) {
                String newLogin = resultadoQ.getString("login");
                System.out.println(newLogin);
                String newSenha = resultadoQ.getString("senha");
                System.out.println(newSenha);

                if (login.equals(newLogin) && senha.equals(newSenha)) {
                    return true;
                }
            }

        } catch (Exception e) {
            System.out.println("Erro ao realizar o login = " + e);
        }
        return false;
    }

    @Override
    public String salvar(Usuario o) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "INSERT INTO usuario VALUES "
                    + "(DEFAULT, "
                    + "'" + o.getNome() + "',"
                    + "'" + o.getLogin() + "',"
                    + "'" + o.getSenha() + "',"
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
            System.out.println("Erro salvar usuário = " + e);
            return e.toString();
        }
    }

    @Override
    public String atualizar(Usuario o) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "UPDATE usuario "
                    + "SET nome = '" + o.getNome() + "', "
                    + "login = '" + o.getLogin() + "', "
                    + "senha = '" + o.getSenha() + "' "
                    + "WHERE id = " + o.getId();

            System.out.println("sql: " + sql);

            int resultado = st.executeUpdate(sql);

            return null;

        } catch (Exception e) {
            System.out.println("Erro ao atualizar usuario = " + e);
            return e.toString();
        }
    }

    @Override
    public String excluir(int id) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "UPDATE usuario "
                    + "SET x = 'Inativo' "
                    + "WHERE id = " + id;

            int resultado = st.executeUpdate(sql);

            return null;

        } catch (Exception e) {
            System.out.println("Erro ao excluir Usuário = " + e);
            return e.toString();
        }
    }

    @Override
    public Usuario consultarId(int id) {
        Usuario usu = null;

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = ""
                    + "SELECT * "
                    + "FROM usuario "
                    + "WHERE id = " + id;

            System.out.println("Sql: " + sql);

            resultadoQ = st.executeQuery(sql);

            while (resultadoQ.next()) {
                usu = new Usuario();

                usu.setId(id);
                usu.setNome(resultadoQ.getString("nome"));
                usu.setLogin(resultadoQ.getString("login"));
                usu.setSenha(resultadoQ.getString("senha"));
            }

        } catch (Exception e) {
            System.out.println("Erro consultar usuario = " + e);
        }

        return usu;
    }


    public boolean Validacontem(String login) {
        try {

            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * "
                    + "FROM usuario "
                    + "WHERE login = '" + login + "' "
                    + "AND x = 'A';";
            System.out.println("Sql = " + sql);

            resultadoQ = st.executeQuery(sql);

            if (resultadoQ.next()) {
                String newLogin = resultadoQ.getString("login");
                System.out.println(newLogin);

                if (login.equals(newLogin)) {
                    return false;
                }
            }

        } catch (Exception e) {
            System.out.println("Erro ao procurar login = " + e);
        }
        return true;
    }   
}
