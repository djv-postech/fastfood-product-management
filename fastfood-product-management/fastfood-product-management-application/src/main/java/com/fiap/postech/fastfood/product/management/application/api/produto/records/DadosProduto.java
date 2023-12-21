package com.fiap.postech.fastfood.product.management.application.api.produto.records;

import com.fiap.postech.fastfood.product.management.core.produto.Categoria;
import com.fiap.postech.fastfood.product.management.core.produto.Produto;

import java.math.BigDecimal;

public record DadosProduto(String id, String nome, String descricao, BigDecimal preco, Categoria produtoCategoria) {

    public DadosProduto(Produto dadosProduto) {
        this(dadosProduto.getId(), dadosProduto.getNome(), dadosProduto.getDescricao(),
                dadosProduto.getPreco(), dadosProduto.getCategoria());
    }
}
