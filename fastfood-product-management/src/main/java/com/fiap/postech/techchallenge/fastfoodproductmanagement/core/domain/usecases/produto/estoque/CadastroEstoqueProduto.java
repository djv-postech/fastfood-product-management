package com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto.estoque;

import com.fiap.postech.techchallenge.fastfoodproductmanagement.application.api.produto.records.DadosCadastroEstoqueProduto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Produto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.ProdutoRepository;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.exception.EstoqueException;

import java.util.Objects;

public class CadastroEstoqueProduto {

    private final ProdutoRepository produtoRepository;

    public CadastroEstoqueProduto(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    public Produto cadastrar(DadosCadastroEstoqueProduto dadosCadastroEstoqueProduto) {
        Produto produto = produtoRepository.listarProdutoPorId(
                dadosCadastroEstoqueProduto.idProduto());

        if(Objects.isNull(produto)){
            throw new EstoqueException(
                    "Produto de id: " + dadosCadastroEstoqueProduto.idProduto() + " não encontrado" );
        }

        produto.adicionarEstoque(dadosCadastroEstoqueProduto.quantidadeEstoque());

        return produtoRepository.cadastrarEstoqueProduto(produto);
    }
}
