package com.fiap.postech.techchallenge.fastfoodproductmanagement.infra.persistence.repository;

import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Categoria;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Produto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.ProdutoRepository;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.infra.persistence.exception.NotFoundException;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.infra.persistence.repository.converter.ProdutoConverter;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.infra.persistence.repository.entity.ProdutoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProdutoRepositoryImpl implements ProdutoRepository {

    private final ProdutoRepositoryMysql produtoRepositoryMysql;
    private final ProdutoConverter produtoConverter;

    @Override
    public Produto cadastrarProduto(Produto produto) {
        ProdutoEntity produtoEntity = produtoRepositoryMysql.save(ProdutoEntity.from(produto));
        return produtoConverter.convertFrom(produtoEntity);
    }

    @Override
    public List<Produto> listarProdutos() {
        return produtoRepositoryMysql.findAll()
                .stream()
                .map(produtoConverter::convertFrom)
                .collect(Collectors.toList());
    }

    @Override
    public Produto listarProdutoPorNome(String nome) {
        return produtoRepositoryMysql.findByNome(nome)
                .map(produtoConverter::convertFrom)
                .orElseThrow(() -> new NotFoundException("Produto: "+ nome +" não encontrado"));
    }

    @Override
    public Produto listarProdutoPorId(Integer id) {
        return produtoRepositoryMysql.findById(id)
                .map(produtoConverter::convertFrom)
                .orElseThrow(() -> new NotFoundException("Produto de Id: "+ id +" não encontrado"));
    }

    @Override
    public void removerProduto(Integer id) {
        produtoRepositoryMysql.deleteById(id);
    }

    @Override
    public Produto atualizarProduto(Produto produto) {
        ProdutoEntity produtoEntity = produtoRepositoryMysql.save(ProdutoEntity.from(produto));
        return produtoConverter.convertFrom(produtoEntity);
    }

    @Override
    public List<Produto> listaProdutosPorCategoria(Categoria categoria) {
        return produtoRepositoryMysql.findByCategoria(categoria).stream()
                .map(produtoConverter::convertFrom)
                .collect(Collectors.toList());
    }
}