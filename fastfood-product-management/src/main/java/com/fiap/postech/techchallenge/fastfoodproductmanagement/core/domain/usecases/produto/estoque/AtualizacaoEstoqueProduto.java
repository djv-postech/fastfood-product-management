package com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto.estoque;

import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Produto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.ProdutoRepository;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.exception.ProdutoException;

import java.util.Objects;

public class AtualizacaoEstoqueProduto {


    private final ProdutoRepository produtoRepository;

    public AtualizacaoEstoqueProduto(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    public Produto atualizar(Integer id, Integer quantidade) {
        Produto produto = produtoRepository.listarProdutoPorId(id);

        if(Objects.isNull(produto)){
            throw new ProdutoException(
                    "Produto de id: " + id + " não encontrado" );
        }

        produto.adicionarEstoque(quantidade);

        return produtoRepository.cadastrarEstoqueProduto(produto);
    }
}
