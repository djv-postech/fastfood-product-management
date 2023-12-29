package com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto.preco;

import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Produto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.ProdutoRepository;

import java.util.List;

public class ListagemPrecoProduto {

    private ProdutoRepository produtoRepository;

    public ListagemPrecoProduto(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listarPrecoProdutos() {
        return this.produtoRepository.listarProdutos();
    }

    public Produto listarPrecoDoProdutoPorId(Integer id) {
        return produtoRepository.listarProdutoPorId(id);
    }
}
