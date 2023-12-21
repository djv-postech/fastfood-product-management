package com.fiap.postech.fastfood.product.management.core.produto;

import java.util.Objects;

public class AtualizacaoDeProduto {

    private final ProdutoRepository produtoRepository;

    public AtualizacaoDeProduto(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    public Produto atualizarDadosProduto(String id, Produto produto) {
        Produto produtoBanco = produtoRepository.listarProdutoPorId(id);

        if (Objects.isNull(produtoBanco)) {
            throw new RuntimeException("Produto não encontrado - id: " + id);
        }

        produtoBanco.setNome(produto.getNome());
        produtoBanco.setDescricao(produto.getDescricao());
        produtoBanco.setPreco(produto.getPreco());
        produtoBanco.setCategoria(produto.getCategoria());
        produtoBanco.setQuantidade(produto.getQuantidade());

        return produtoRepository.atualizarProduto(produtoBanco);
    }
}
