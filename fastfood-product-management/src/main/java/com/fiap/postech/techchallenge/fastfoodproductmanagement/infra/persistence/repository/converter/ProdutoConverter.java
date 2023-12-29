package com.fiap.postech.techchallenge.fastfoodproductmanagement.infra.persistence.repository.converter;

import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Produto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.infra.persistence.repository.entity.ProdutoEntity;
import org.springframework.stereotype.Component;

@Component
public class ProdutoConverter {

    public Produto convertFrom(ProdutoEntity produtoEntity){
        return new Produto(produtoEntity.getId(), produtoEntity.getNome(), produtoEntity.getDescricao(), produtoEntity.getPreco(),
                produtoEntity.getQuantidadeEstoque(), produtoEntity.getCategoria());
    }
}
