package com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto.preco;

import com.fiap.postech.techchallenge.fastfoodproductmanagement.application.api.produto.records.DadosCadastroPrecificacaoProduto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Produto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.ProdutoRepository;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.exception.ProdutoException;

import java.util.Objects;

public class CadastroPrecoProduto {

    private final ProdutoRepository produtoRepository;

    public CadastroPrecoProduto(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto cadastrar(DadosCadastroPrecificacaoProduto dadosCadastroPrecificacaoProduto) {
        Produto produto = produtoRepository.listarProdutoPorId(
                dadosCadastroPrecificacaoProduto.idProduto());

        if (Objects.isNull(produto)) {
            throw new ProdutoException("Produto de id " + dadosCadastroPrecificacaoProduto.idProduto() + " não encontrado no catálogo.");
        }

        produto.precificarProduto(dadosCadastroPrecificacaoProduto.preco());

        return produtoRepository.cadastrarEstoqueProduto(produto);

    }
}
