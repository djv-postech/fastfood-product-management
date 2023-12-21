package com.fiap.postech.fastfood.product.management.infra.persistence.repository.converter;

import com.fiap.postech.fastfood.product.management.core.produto.Produto;
import com.fiap.postech.fastfood.product.management.infra.persistence.repository.entity.ProdutoEntity;
import org.springframework.stereotype.Component;

@Component
public class ProdutoConverter {

    public Produto convertFrom(ProdutoEntity produtoEntity){
        return new Produto(produtoEntity.getId(), produtoEntity.getNome(), produtoEntity.getDescricao(), produtoEntity.getPreco(),
                produtoEntity.getQuantidade(), produtoEntity.getCategoria());
    }
}
