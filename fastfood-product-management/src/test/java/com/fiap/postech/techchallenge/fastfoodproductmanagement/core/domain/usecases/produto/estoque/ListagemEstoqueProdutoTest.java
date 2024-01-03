package com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto.estoque;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

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



@ExtendWith(MockitoExtension.class)
class ListagemEstoqueProdutoTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ListagemEstoqueProduto listagemEstoqueProduto;

    @Test
    public void devePermitirRetornarEstoqueDeTodosOsProdutosDoCatalogo(){
        //Arrange
        List<Produto> produtos = new ArrayList<>();
        produtos.add(ProdutoHelper.gerarProdutoCompleto(1, "Big Mac", "Descricao Big Mac", Categoria.LANCHE, new BigDecimal(10), 50));
        produtos.add(ProdutoHelper.gerarProdutoCompleto(2, "Mc Flury", "Descricao Mac Flury", Categoria.LANCHE, new BigDecimal(5), 1));

        Mockito.when(produtoRepository.listarProdutos()).thenReturn(produtos);

        //Act
        List<Produto> produtosEstoque = listagemEstoqueProduto.total();
        //Assert
        Assertions.assertThat(produtosEstoque).isNotNull();
        Assertions.assertThat(produtosEstoque.size()).isEqualTo(2);
        Assertions.assertThat(produtosEstoque.get(0).getQuantidadeEstoque()).isEqualTo(50);
        Assertions.assertThat(produtosEstoque.get(1).getQuantidadeEstoque()).isEqualTo(1);
    }

    @Test
    public void devePermitirRetornarEstoqueDeUmProdutoDoCatalogo(){
        //Arrange
        Produto produto = ProdutoHelper
                .gerarProdutoCompleto(1, "Big Mac", "Descricao Big Mac", Categoria.LANCHE, new BigDecimal(10), 50);


        Mockito.when(produtoRepository.listarProdutoPorId(produto.getId())).thenReturn(produto);

        //Act
        Produto produtosEstoque = listagemEstoqueProduto.listarEstoqueProdutoPorId(1);

        //Assert
        Assertions.assertThat(produtosEstoque).isNotNull();
        Assertions.assertThat(produto.getQuantidadeEstoque()).isEqualTo(produto.getQuantidadeEstoque());
    }

    @Test
    public void deveLancarExcecaoAoBuscarEstoqueDeProdutoInexistenteNoCatalogo(){
        //Arrange
        Produto produto = ProdutoHelper
                .gerarProdutoCompleto(1, "Big Mac", "Descricao Big Mac", Categoria.LANCHE, new BigDecimal(10), 50);


        Mockito.when(produtoRepository.listarProdutoPorId(produto.getId())).thenReturn(null);

        assertThatThrownBy(() -> listagemEstoqueProduto.listarEstoqueProdutoPorId(produto.getId()))
                .isInstanceOf(ProdutoNotFoundException.class)
                .hasMessageContaining("Produto de id "  + produto.getId() + " não encontrado no catálogo.");


    }

}