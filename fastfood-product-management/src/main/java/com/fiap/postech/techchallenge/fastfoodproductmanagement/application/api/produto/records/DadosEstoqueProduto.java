package com.fiap.postech.techchallenge.fastfoodproductmanagement.application.api.produto.records;

import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Produto;

public record DadosEstoqueProduto(Integer idProduto, String nome, Integer quantidadeEstoque) {
    public DadosEstoqueProduto(Produto produto) {
        this(produto.getId(), produto.getNome(), produto.getQuantidadeEstoque());
    }
}
