package com.fiap.postech.fastfood.product.management.core.produto;


import java.util.List;

public class ListagemDeProdutosPorCategoria {

    private final ProdutoRepository produtoRepository;

    public ListagemDeProdutosPorCategoria(ProdutoRepository clienteRepository) {
        this.produtoRepository = clienteRepository;
    }

    public List<Produto> listaProdutosPorCategoria(Categoria categoria) {
        return produtoRepository.listaProdutosPorCategoria(categoria);
    }
}
