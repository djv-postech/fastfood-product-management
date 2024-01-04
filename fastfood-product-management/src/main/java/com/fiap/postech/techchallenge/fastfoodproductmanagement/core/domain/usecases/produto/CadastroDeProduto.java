package com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto;


import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Produto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.ProdutoRepository;

public class CadastroDeProduto {

  private final ProdutoRepository produtoRepository;

  public CadastroDeProduto(ProdutoRepository produtoRepository) {
    this.produtoRepository = produtoRepository;
  }

  public Produto cadastrar(Produto produto) {
    return produtoRepository.cadastrarProduto(produto);
  }
}
