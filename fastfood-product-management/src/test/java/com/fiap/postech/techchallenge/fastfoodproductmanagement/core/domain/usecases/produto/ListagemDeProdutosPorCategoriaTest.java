package com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto;

import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Categoria;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Produto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.ProdutoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ListagemDeProdutosPorCategoriaTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ListagemDeProdutosPorCategoria listagemDeProdutosPorCategoria;

    @Test
    public void devePermitirListarOsProdutosDoCatalogoPorCategoria(){
        //Arrange
        List<Produto> produtos = new ArrayList<>();
        produtos.add(ProdutoHelper.gerarProduto("Big Mac", "Descricao Big Mac", Categoria.LANCHE));
        produtos.add(ProdutoHelper.gerarProduto("Big Chicken", "Descricao Big Chicken", Categoria.LANCHE));

        Mockito.when(produtoRepository.listaProdutosPorCategoria(Categoria.LANCHE)).thenReturn(produtos);

        //Act
        List<Produto> produtosPorCategoria = listagemDeProdutosPorCategoria
                .listaProdutosPorCategoria(Categoria.LANCHE);

        //Assert
        Assertions.assertThat(produtosPorCategoria.size()).isEqualTo(2);
        Assertions.assertThat(produtosPorCategoria.get(0).getCategoria())
                .isEqualTo(produtos.get(0).getCategoria());
    }
}