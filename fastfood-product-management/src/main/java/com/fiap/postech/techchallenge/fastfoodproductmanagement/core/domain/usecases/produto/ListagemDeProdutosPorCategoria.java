package com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto;

import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Categoria;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Produto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.ProdutoRepository;

import java.util.List;


public class ListagemDeProdutosPorCategoria {

    private final ProdutoRepository produtoRepository;

    public ListagemDeProdutosPorCategoria(ProdutoRepository clienteRepository) {
        this.produtoRepository = clienteRepository;
    }

    public List<Produto> listaProdutosPorCategoria(Categoria categoria) {
        return produtoRepository.listaProdutosPorCategoria(categoria);
    }
}
