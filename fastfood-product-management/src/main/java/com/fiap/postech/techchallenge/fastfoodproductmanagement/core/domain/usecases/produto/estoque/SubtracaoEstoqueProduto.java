package com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto.estoque;

import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Produto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.ProdutoRepository;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.exception.ProdutoNotFoundException;

import java.util.Objects;

public class SubtracaoEstoqueProduto {

    private final ProdutoRepository produtoRepository;

    public SubtracaoEstoqueProduto(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }


    public Produto subtrair(Integer idProduto, Integer quantidade) {
        Produto produto = produtoRepository.listarProdutoPorId(idProduto);

        if(Objects.isNull(produto)){
            throw new ProdutoNotFoundException(
                    "Produto de id "  + idProduto + " não encontrado" );
        }

        produto.subtrairEstoque(quantidade);

        return produtoRepository.cadastrarEstoqueProduto(produto);
    }
}
