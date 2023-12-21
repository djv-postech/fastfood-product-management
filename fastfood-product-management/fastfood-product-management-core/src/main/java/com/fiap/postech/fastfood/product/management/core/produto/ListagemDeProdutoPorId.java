package com.fiap.postech.fastfood.product.management.core.produto;

import java.util.Objects;

public class ListagemDeProdutoPorId {

    private final ProdutoRepository produtoRepository;

    public ListagemDeProdutoPorId(ProdutoRepository clienteRepository) {
        this.produtoRepository = clienteRepository;
    }

    public Produto listarProdutoPorId(String id) {
        Produto dadosProduto = produtoRepository.listarProdutoPorId(id);
        return (Objects.isNull(dadosProduto) ? null : dadosProduto);
    }

}
