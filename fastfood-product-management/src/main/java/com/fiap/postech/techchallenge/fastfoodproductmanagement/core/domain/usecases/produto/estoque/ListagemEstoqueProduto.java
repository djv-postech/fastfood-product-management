package com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto.estoque;

import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Produto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.ProdutoRepository;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.exception.ProdutoNotFoundException;

import java.util.List;
import java.util.Objects;

public class ListagemEstoqueProduto {

    private final ProdutoRepository produtoRepository;

    public ListagemEstoqueProduto( ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> total() {
        return produtoRepository.listarProdutos();
    }

    public Produto listarEstoqueProdutoPorId(Integer id) {
        Produto produto = produtoRepository.listarProdutoPorId(id);

        if (Objects.isNull(produto)) {
            throw new ProdutoNotFoundException("Produto de id " + id + " não encontrado no catálogo.");
        }

        return produto;
    }
}
