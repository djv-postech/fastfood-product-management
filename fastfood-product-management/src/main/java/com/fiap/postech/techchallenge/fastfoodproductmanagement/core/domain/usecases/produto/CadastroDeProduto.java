package com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto;


import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Produto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.ProdutoRepository;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.exception.ProdutoException;

import java.util.Objects;

public class CadastroDeProduto {

  private final ProdutoRepository produtoRepository;

  public CadastroDeProduto(ProdutoRepository produtoRepository) {
    this.produtoRepository = produtoRepository;
  }

  public Produto cadastrar(Produto produto) {

//    if (validaProdutoCadastrado(produto)) {
//      throw new ProdutoException("Produto: " + produto.getNome() + " já cadastrado no catálogo");
//    }

    return produtoRepository.cadastrarProduto(produto);

  }

  private boolean validaProdutoCadastrado(Produto produto) {
    return Objects.nonNull(produtoRepository.listarProdutoPorNome(produto.getNome()));
  }

}
