package com.fiap.postech.fastfood.product.management.core.produto;

public class ExclusaoDeProduto {
    private final ProdutoRepository produtoRepository;

    public ExclusaoDeProduto(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    public void removerProdutoDoCatalogo(String id) {
        produtoRepository.removerProduto(id);
    }
}
