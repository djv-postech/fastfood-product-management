package com.fiap.postech.fastfood.product.management.core.produto;

import java.math.BigDecimal;

public class Produto {

    private String id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Integer quantidade;
    private Categoria categoria;

    public Produto(){

    }

    public Produto(String id, String nome, String descricao, BigDecimal preco, Integer quantidade, Categoria categoria) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
    }

    public Produto(String nome, String descricao, Categoria categoria, BigDecimal preco) {
        this.nome = nome;
        this.descricao = descricao;
        this.categoria = categoria;
        this.preco = preco;
    }

    public String getId() {
        return id;
    }

    public String getNome(){
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

}
