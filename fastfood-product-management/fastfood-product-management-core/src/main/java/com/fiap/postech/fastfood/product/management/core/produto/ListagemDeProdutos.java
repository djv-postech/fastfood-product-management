package com.fiap.postech.fastfood.product.management.core.produto;

import java.util.List;

public class ListagemDeProdutos {

    private final ProdutoRepository produtoRepository;

    public ListagemDeProdutos(ProdutoRepository clienteRepository) {
        this.produtoRepository = clienteRepository;
    }

    public List<Produto> listarTodosOsProdutos() {
        return produtoRepository.listarProdutos();
    }

}
