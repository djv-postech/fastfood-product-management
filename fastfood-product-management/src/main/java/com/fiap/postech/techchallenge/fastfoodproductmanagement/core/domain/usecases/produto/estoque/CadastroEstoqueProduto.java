package com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto.estoque;

import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Produto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.ProdutoRepository;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.exception.EstoqueException;

import java.util.Objects;

public class CadastroEstoqueProduto {

    private final ProdutoRepository produtoRepository;

    public CadastroEstoqueProduto(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    public Produto cadastrar(Integer idProduto, Integer quantidade) {
        Produto produto = produtoRepository.listarProdutoPorId(idProduto);

        if(Objects.isNull(produto)){
            throw new EstoqueException(
                    "Produto de id: " + idProduto + " não encontrado" );
        }

        produto.adicionarEstoque(quantidade);

        return produtoRepository.cadastrarEstoqueProduto(produto);
    }
}
