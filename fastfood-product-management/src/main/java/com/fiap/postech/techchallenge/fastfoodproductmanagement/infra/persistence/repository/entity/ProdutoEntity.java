package com.fiap.postech.techchallenge.fastfoodproductmanagement.infra.persistence.repository.entity;

import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Categoria;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Produto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;


import java.math.BigDecimal;

@Builder
@Entity
public class ProdutoEntity {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String nome;
  private String descricao;
  private BigDecimal preco;
  private Integer quantidade;
  private Categoria categoria;

  public ProdutoEntity(
      Integer id,
      String nome,
      String descricao,
      BigDecimal preco,
      Integer quantidade,
      Categoria categoria) {
    this.id = id;
    this.nome = nome;
    this.descricao = descricao;
    this.preco = preco;
    this.quantidade = quantidade;
    this.categoria = categoria;
  }

  public ProdutoEntity(
          String nome,
          String descricao,
          BigDecimal preco,
          Integer quantidade,
          Categoria categoria) {
    this.nome = nome;
    this.descricao = descricao;
    this.preco = preco;
    this.quantidade = quantidade;
    this.categoria = categoria;
  }

  public ProdutoEntity() {}

  public static ProdutoEntity from(Produto produto) {
    return new ProdutoEntity(produto.getId(), produto.getNome(), produto.getDescricao(),produto.getPreco(), produto.getQuantidade(), produto.getCategoria());
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

  public Integer getQuantidade() {
    return quantidade;
  }

  public Categoria getCategoria() {
    return categoria;
  }
}
