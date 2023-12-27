package com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto;

import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.ProdutoRepository;

public class ExclusaoDeProduto {
    private final ProdutoRepository produtoRepository;

    public ExclusaoDeProduto(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    public void removerProdutoDoCatalogo(Integer id) {
        produtoRepository.removerProduto(id);
    }
}
