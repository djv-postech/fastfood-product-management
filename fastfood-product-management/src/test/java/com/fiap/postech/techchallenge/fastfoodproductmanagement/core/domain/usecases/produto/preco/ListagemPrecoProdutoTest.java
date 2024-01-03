package com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto.preco;

import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Categoria;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Produto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.ProdutoRepository;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.exception.ProdutoNotFoundException;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto.ProdutoHelper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class ListagemPrecoProdutoTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ListagemPrecoProduto listagemPrecoProduto;


    @Test
    public void devePermitirListarOPrecoDeTodosOsProdutosDoCatalogo(){
        //Arrange
        List<Produto> produtos = new ArrayList<>();
        produtos.add(ProdutoHelper.gerarProdutoCompleto(1, "Big Mac", "Descricao Big Mac", Categoria.LANCHE, new BigDecimal(10), 50));
        produtos.add(ProdutoHelper.gerarProdutoCompleto(2, "Mc Flury", "Descricao Mac Flury", Categoria.LANCHE, new BigDecimal(5), 1));

        Mockito.when(produtoRepository.listarProdutos()).thenReturn(produtos);

        //Act
        List<Produto> precoProduto = listagemPrecoProduto.listarPrecoProdutos();

        //Assert
        Assertions.assertThat(precoProduto).isNotNull();
        Assertions.assertThat(precoProduto.size()).isEqualTo(2);
    }

    @Test
    public void deveLancarExcecaoAoBuscarPrecoDeProdutoInexistenteNoCatalogo(){
        //Arrange
        Produto produto = ProdutoHelper
                .gerarProdutoCompleto(1, "Big Mac", "Descricao Big Mac", Categoria.LANCHE, new BigDecimal(10), 50);


        Mockito.when(produtoRepository.listarProdutoPorId(produto.getId())).thenReturn(null);

        assertThatThrownBy(() -> listagemPrecoProduto.listarPrecoDoProdutoPorId(produto.getId()))
                .isInstanceOf(ProdutoNotFoundException.class)
                .hasMessageContaining("Produto de id "  + produto.getId() + " não encontrado no catálogo.");


    }

}