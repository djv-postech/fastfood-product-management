package com.fiap.postech.fastfood.product.management.core.produto;


public class CadastroDeProduto {

  private final ProdutoRepository produtoRepository;

  public CadastroDeProduto(ProdutoRepository produtoRepository) {
    this.produtoRepository = produtoRepository;
  }

  public Produto cadastrar(Produto produto) {

    if (produtoJaCadastrado(produto)) {
      throw new RuntimeException("Produto: " + produto.getNome() + " já cadastrado");
    }

    return produtoRepository.cadastrarProduto(produto);

  }

  private boolean produtoJaCadastrado(Produto produto) {
    try {
      Produto produtoJaCadastrado = produtoRepository.listarProdutoPorNome(produto.getNome());
      return produtoJaCadastrado != null;
    } catch (RuntimeException ex) {
      return false;
    }
  }



}
