package com.fiap.postech.fastfood.product.management.core.produto;

import java.util.List;

public interface ProdutoRepository {

    Produto cadastrarProduto(Produto produto);

    Produto listarProdutoPorId(Long id);

    Produto atualizarProduto(Produto produto);

    void removerProduto(Long id);

    List<Produto> listarProdutos();

    List<Produto> listaProdutosPorCategoria(Categoria categoria);

    Produto listarProdutoPorNome(String nome);
}
