package com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto.estoque;

import com.fiap.postech.techchallenge.fastfoodproductmanagement.application.api.produto.records.DadosSubtracaoEstoqueProduto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Produto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.ProdutoRepository;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.exception.ProdutoNotFoundException;

import java.util.ArrayList;
import java.util.List;
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

    public void subtrairEstoqueListaProdutos(List<DadosSubtracaoEstoqueProduto> dadosSubtracaoEstoqueProduto) {

        verificaDisponibilidadeEstoque(dadosSubtracaoEstoqueProduto);

        dadosSubtracaoEstoqueProduto.forEach(produtoEstoque -> {
            Produto produto = produtoRepository.listarProdutoPorId(produtoEstoque.idProduto());

            produto.subtrairEstoque(produtoEstoque.quantidade());

            produtoRepository.cadastrarEstoqueProduto(produto);

        });
    }

    private void verificaDisponibilidadeEstoque(List<DadosSubtracaoEstoqueProduto> dadosSubtracaoEstoqueProduto) {
        dadosSubtracaoEstoqueProduto.forEach(produto ->{
                Produto produtoEstoque = produtoRepository.listarProdutoPorId(produto.idProduto());

                if(Objects.isNull(produtoEstoque)){
                    throw new ProdutoNotFoundException(
                            "Produto de id "  + produto.idProduto() + " não encontrado" );
                }

                if(produtoEstoque.getQuantidadeEstoque() - produto.quantidade() < 0){
                    throw new ProdutoNotFoundException(
                            "Produto de id "  + produto.idProduto() + " não possui estoque suficiente");
                }

            });
    }
}
