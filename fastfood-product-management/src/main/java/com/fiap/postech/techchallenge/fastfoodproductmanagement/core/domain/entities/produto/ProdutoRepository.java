package com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto;

import java.util.List;

public interface ProdutoRepository {

    Produto cadastrarProduto(Produto produto);

    Produto listarProdutoPorId(Integer id);

    Produto atualizarProduto(Produto produto);

    void removerProduto(Integer id);

    List<Produto> listarProdutos();

    List<Produto> listaProdutosPorCategoria(Categoria categoria);

    Produto listarProdutoPorNome(String nome);

    Produto cadastrarEstoqueProduto(Produto produto);

    Produto cadastrarPrecoProduto(Produto produto);

}
