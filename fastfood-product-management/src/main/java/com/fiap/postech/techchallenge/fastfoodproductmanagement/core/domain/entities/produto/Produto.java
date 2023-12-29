package com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto;

import java.math.BigDecimal;

public class Produto {

    private Integer id;
    private String nome;
    private String descricao;
    private Categoria categoria;
    private BigDecimal preco;
    private Integer quantidadeEstoque;


    public Produto(){

    }

    public Produto(Integer id, String nome, String descricao, BigDecimal preco, Integer quantidadeEstoque, Categoria categoria) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public Produto(String nome, String descricao, Categoria categoria) {
        this.nome = nome;
        this.descricao = descricao;
        this.categoria = categoria;
    }

    public Integer getId() {
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

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
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

    public void precificarProduto(BigDecimal precoProduto) {
        if(precoProduto.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("O preço do produto não pode ser negativo");
        }

        this.preco = precoProduto;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void adicionarEstoque(Integer quantidade) {
        if(quantidade < 0){
            throw new IllegalArgumentException("Estoque não pode ser negativo");
        }

        if(this.quantidadeEstoque == 0){
            this.quantidadeEstoque = quantidade;
        }else{
            this.quantidadeEstoque += quantidade;
        }
    }

    public void subtrairEstoque(Integer quantidadeSubtrair){
        if(quantidadeSubtrair > this.quantidadeEstoque){
            throw new IllegalArgumentException("Estoque não pode ser negativo");
        }
        this.quantidadeEstoque-=quantidadeSubtrair;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

}
