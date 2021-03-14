/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidade;

/**
 *
 * @author PC
 */
public class CompraProduto {

    private int id;
    private int quantidade;
    private double preco;
    private int produtoId;
    private int compraId;
    private int compraFornecedorId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    public int getCompraId() {
        return compraId;
    }

    public void setCompraId(int compraId) {
        this.compraId = compraId;
    }

    public int getCompraFornecedorId() {
        return compraFornecedorId;
    }

    public void setCompraFornecedorId(int compraFornecedorId) {
        this.compraFornecedorId = compraFornecedorId;
    }

}
