package com.fiap.postech.techchallenge.fastfoodproductmanagement.application.api.produto.records;

import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Produto;

import java.math.BigDecimal;

public record DadosPrecificacaoProduto(Integer id, String nome, BigDecimal preco) {

    public DadosPrecificacaoProduto(Produto produto){
        this(produto.getId(), produto.getNome(), produto.getPreco());
    }
}
