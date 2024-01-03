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

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ListagemDeProdutosTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ListagemDeProdutos listagemDeProdutos;

    @Test
    public void devePermitirListarTodosOsProdutosDoCatalogo(){
        List<Produto> listaProdutos = Arrays.asList(
                ProdutoHelper.gerarProduto("Big Mac", "Descricao Big Mac", Categoria.LANCHE),
                ProdutoHelper.gerarProduto("Big Tasty", "Descricao Big Tasty", Categoria.LANCHE));

        //Assert
        Mockito.when(produtoRepository.listarProdutos()).thenReturn(listaProdutos);

        //Act
        List<Produto> produtos = listagemDeProdutos.listarTodosOsProdutos();

        //Arrange
        Assertions.assertThat(produtos).isNotNull();
        Assertions.assertThat(produtos.size()).isEqualTo(2);
    }

}