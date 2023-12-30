package com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto;

import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Produto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.ProdutoRepository;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.exception.ProdutoException;

import java.util.Objects;

public class AtualizacaoDeProduto {

    private final ProdutoRepository produtoRepository;

    public AtualizacaoDeProduto(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    public Produto atualizarDadosProduto(Integer id, Produto produto) {
        Produto produtoBanco = produtoRepository.listarProdutoPorId(id);

        if (Objects.isNull(produtoBanco)) {
            throw new ProdutoException("Produto de id " + id + " não encontrado no catálogo.");
        }

        produtoBanco.setNome(produto.getNome());
        produtoBanco.setDescricao(produto.getDescricao());
        produtoBanco.setCategoria(produto.getCategoria());

        return produtoRepository.atualizarProduto(produtoBanco);
    }
}
