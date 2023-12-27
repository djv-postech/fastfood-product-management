package com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto;

import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Produto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.ProdutoRepository;

import java.util.Objects;

public class ListagemDeProdutoPorId {

    private final ProdutoRepository produtoRepository;

    public ListagemDeProdutoPorId(ProdutoRepository clienteRepository) {
        this.produtoRepository = clienteRepository;
    }

    public Produto listarProdutoPorId(Integer id) {
        Produto dadosProduto = produtoRepository.listarProdutoPorId(id);
        return (Objects.isNull(dadosProduto) ? null : dadosProduto);
    }

}
