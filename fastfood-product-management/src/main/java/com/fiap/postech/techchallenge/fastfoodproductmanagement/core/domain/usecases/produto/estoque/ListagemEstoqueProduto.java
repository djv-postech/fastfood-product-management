package com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto.estoque;

import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Produto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.ProdutoRepository;

import java.util.List;

public class ListagemEstoqueProduto {

    private final ProdutoRepository produtoRepository;

    public ListagemEstoqueProduto( ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> total() {
        return produtoRepository.listarProdutos();
    }

    public Produto listarEstoqueProdutoPorId(Integer id) {
        return produtoRepository.listarProdutoPorId(id);
    }
}
