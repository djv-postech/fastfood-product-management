package com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto.preco;

import com.fiap.postech.techchallenge.fastfoodproductmanagement.application.api.produto.records.DadosAtualizacaoPrecificacaoProduto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Produto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.ProdutoRepository;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.exception.ProdutoException;

import java.util.Objects;

public class AtualizacaoPrecoProduto {

    private final ProdutoRepository produtoRepository;

    public AtualizacaoPrecoProduto(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto atualizar(Integer id, DadosAtualizacaoPrecificacaoProduto dadosAtualizacaoPrecificacaoProduto) {

        Produto produto = produtoRepository.listarProdutoPorId(id);

        if (Objects.isNull(produto)) {
            throw new ProdutoException("Produto de id " + id + " não encontrado no catálogo.");
        }

        produto.precificarProduto(dadosAtualizacaoPrecificacaoProduto.preco());

        return produtoRepository.cadastrarEstoqueProduto(produto);
    }
}
