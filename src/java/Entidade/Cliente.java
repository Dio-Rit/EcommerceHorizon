/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidade;

/**
 *
 * @author dionatan.ritter
 */
public class Cliente {

    private int id;
    private String nome;
    private String cpf;
    private String dataNsci;
    private String email;
    private String decricao;
    private String X;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDataNsci() {
        return dataNsci;
    }

    public void setDataNsci(String dataNsci) {
        this.dataNsci = dataNsci;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDecricao() {
        return decricao;
    }

    public void setDecricao(String decricao) {
        this.decricao = decricao;
    }

    public String getX() {
        return X;
    }

    public void setX(String X) {
        this.X = X;
    }

}
