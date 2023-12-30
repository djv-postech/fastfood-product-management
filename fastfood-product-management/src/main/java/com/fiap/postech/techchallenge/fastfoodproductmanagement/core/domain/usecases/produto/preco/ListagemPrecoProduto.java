package com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto.preco;

import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Produto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.ProdutoRepository;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.exception.ProdutoException;

import java.util.List;
import java.util.Objects;

public class ListagemPrecoProduto {

    private ProdutoRepository produtoRepository;

    public ListagemPrecoProduto(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listarPrecoProdutos() {
        return this.produtoRepository.listarProdutos();
    }

    public Produto listarPrecoDoProdutoPorId(Integer id) {

        Produto produto = produtoRepository.listarProdutoPorId(id);

        if (Objects.isNull(produto)) {
            throw new ProdutoException("Produto de id " + id + " não encontrado no catálogo.");
        }

        return produto;

    }
}
