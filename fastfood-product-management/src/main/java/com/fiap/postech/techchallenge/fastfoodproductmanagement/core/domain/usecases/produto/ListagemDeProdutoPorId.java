package com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto;

import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Produto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.ProdutoRepository;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.exception.ProdutoException;

import java.util.Objects;

public class ListagemDeProdutoPorId {

    private final ProdutoRepository produtoRepository;

    public ListagemDeProdutoPorId(ProdutoRepository clienteRepository) {
        this.produtoRepository = clienteRepository;
    }

    public Produto listarProdutoPorId(Integer id) {
        Produto produto = produtoRepository.listarProdutoPorId(id);

        if (Objects.isNull(produto)) {
            throw new ProdutoException("Produto de id " + id + " não encontrado no catálogo.");
        }

        return produto;
    }

}
