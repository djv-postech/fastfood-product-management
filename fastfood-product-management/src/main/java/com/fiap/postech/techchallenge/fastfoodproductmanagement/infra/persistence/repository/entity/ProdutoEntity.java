package com.fiap.postech.techchallenge.fastfoodproductmanagement.infra.persistence.repository.entity;

import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Categoria;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Produto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;


import java.math.BigDecimal;

@Entity
public class ProdutoEntity {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String nome;
  private String descricao;
  private BigDecimal preco = BigDecimal.ZERO;
  private Integer quantidadeEstoque = 0;
  private Categoria categoria;

  public ProdutoEntity(
          Integer id,
          String nome,
          String descricao,
          Categoria categoria) {
    this.id = id;
    this.nome = nome;
    this.descricao = descricao;
    this.categoria = categoria;
  }

  public ProdutoEntity(
          Integer id,
          String nome,
          String descricao,
          Categoria categoria,
          BigDecimal preco) {
    this.id = id;
    this.nome = nome;
    this.descricao = descricao;
    this.categoria = categoria;
    this.preco = preco;
  }

  public ProdutoEntity() {}

  public static ProdutoEntity from(Produto produto) {
    return new ProdutoEntity(produto.getId(),
            produto.getNome(),
            produto.getDescricao(),
            produto.getCategoria());
  }

  public static ProdutoEntity fromWithPrice(Produto produto) {
    return new ProdutoEntity(produto.getId(),
            produto.getNome(),
            produto.getDescricao(),
            produto.getCategoria(),
            produto.getPreco());
  }

  public Integer getId() {
    return id;
  }

  public String getNome() {
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

  public void setQuantidadeEstoque(Integer quantidadeEstoque){this.quantidadeEstoque = quantidadeEstoque;}
}
