package com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto.estoque;

import com.fiap.postech.techchallenge.fastfoodproductmanagement.application.api.produto.records.DadosSubtracaoEstoqueProduto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Categoria;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Produto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.ProdutoRepository;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.exception.EstoqueException;
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
class SubtracaoEstoqueProdutoTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private SubtracaoEstoqueProduto subtracaoEstoqueProduto;

    @Test
    public void devePermitirSubtrairEstoqueDeProdutoDoCatalogo(){
        //Arrange
        Produto produto = ProdutoHelper
                .gerarProdutoCompleto(1, "Big Mac", "Descricao Big Mac", Categoria.LANCHE, new BigDecimal(10), 50);

        Mockito.when(produtoRepository.listarProdutoPorId(produto.getId())).thenReturn(produto);

        Mockito.when(produtoRepository.cadastrarEstoqueProduto(produto)).thenAnswer(answer -> answer.getArgument(0));

        //Act
        Produto produtoEstoque = subtracaoEstoqueProduto.subtrair(produto.getId(), 10);

        //Assert
        Assertions.assertThat(produtoEstoque).isInstanceOf(Produto.class).isNotNull();
        Assertions.assertThat(produtoEstoque.getQuantidadeEstoque()).isEqualTo(40);
    }

    @Test
    public void devePermitirSubtrairEstoqueDeListaDeProdutosDoCatalogo(){
        //Arrange
        Produto produto = ProdutoHelper
                .gerarProdutoCompleto(1, "Big Mac", "Descricao Big Mac", Categoria.LANCHE, BigDecimal.TEN, 10);
        DadosSubtracaoEstoqueProduto dadosSubtracaoEstoqueProduto = new DadosSubtracaoEstoqueProduto(2, 1);

        List<DadosSubtracaoEstoqueProduto> dadosSubtracaoEstoqueProdutoList = new ArrayList<>();
        dadosSubtracaoEstoqueProdutoList.add(dadosSubtracaoEstoqueProduto);

        Mockito.when(produtoRepository.listarProdutoPorId(dadosSubtracaoEstoqueProduto.idProduto()))
                .thenReturn(produto);

        Mockito.when(produtoRepository.cadastrarEstoqueProduto(Mockito.any(Produto.class)))
                .thenReturn(produto);

        //Act
        subtracaoEstoqueProduto.subtrairEstoqueListaProdutos(dadosSubtracaoEstoqueProdutoList);
        //Assert

        Mockito.verify(produtoRepository,
                Mockito.times(1)).cadastrarEstoqueProduto(Mockito.any(Produto.class));
    }

    @Test
    public void deveLancarExcecaoQuandoSubtrairEstoqueDeListaDeProdutosDoCatalogoEProdutoNaoExistir(){
        //Arrange
        DadosSubtracaoEstoqueProduto dadosSubtracaoEstoqueProduto = new DadosSubtracaoEstoqueProduto(2, 1);

        List<DadosSubtracaoEstoqueProduto> dadosSubtracaoEstoqueProdutoList = new ArrayList<>();
        dadosSubtracaoEstoqueProdutoList.add(dadosSubtracaoEstoqueProduto);

        Mockito.when(produtoRepository.listarProdutoPorId(dadosSubtracaoEstoqueProduto.idProduto()))
                .thenReturn(null);

        //Act
        Assertions.assertThatThrownBy(() ->  subtracaoEstoqueProduto
                .subtrairEstoqueListaProdutos(dadosSubtracaoEstoqueProdutoList))
                .isInstanceOf(ProdutoNotFoundException.class)
                .hasMessageContaining("Produto de id "  + dadosSubtracaoEstoqueProduto.idProduto() + " não encontrado");

        //Assert

        Mockito.verify(produtoRepository,
                Mockito.times(0)).cadastrarEstoqueProduto(Mockito.any(Produto.class));
    }

    @Test
    public void deveLancarExcecaoQuandoSubtrairEstoqueDeListaDeProdutosDoCatalogoEEstoqueNaoForSuficiente(){
        //Arrange
        Produto produto = ProdutoHelper
                .gerarProdutoCompleto(1, "Big Mac", "Descricao Big Mac", Categoria.LANCHE, BigDecimal.TEN, 1);

        DadosSubtracaoEstoqueProduto dadosSubtracaoEstoqueProduto = new DadosSubtracaoEstoqueProduto(1, 2);

        List<DadosSubtracaoEstoqueProduto> dadosSubtracaoEstoqueProdutoList = new ArrayList<>();
        dadosSubtracaoEstoqueProdutoList.add(dadosSubtracaoEstoqueProduto);

        Mockito.when(produtoRepository.listarProdutoPorId(dadosSubtracaoEstoqueProduto.idProduto()))
                .thenReturn(produto);

        //Act
        Assertions.assertThatThrownBy(() ->  subtracaoEstoqueProduto
                .subtrairEstoqueListaProdutos(dadosSubtracaoEstoqueProdutoList))
                .isInstanceOf(ProdutoNotFoundException.class)
                .hasMessageContaining("Produto de id "  + dadosSubtracaoEstoqueProduto.idProduto() + " não possui estoque suficiente");

        //Assert

        Mockito.verify(produtoRepository,
                Mockito.times(0)).cadastrarEstoqueProduto(Mockito.any(Produto.class));
    }

    @Test
    public void deveLancarExcecaoQuandoTentarSubstrairEstoqueDeProdutoInexistente(){
        //Arrange
        Produto produto = ProdutoHelper
                .gerarProdutoCompleto(1, "Big Mac", "Descricao Big Mac", Categoria.LANCHE, new BigDecimal(10), 50);

        Mockito.when(produtoRepository.listarProdutoPorId(produto.getId())).thenReturn(null);

        //Act
        Assertions.assertThatThrownBy(() ->
                subtracaoEstoqueProduto.subtrair(produto.getId(), 10))
                .isInstanceOf(ProdutoNotFoundException.class)
                .hasMessageContaining("Produto de id "  + produto.getId() + " não encontrado");
        }

    @Test
    public void deveLancarExcecaoQuandoTentarSubstrairQuantidadeEstoqueDeProdutoMaiorQueExistente(){
        //Arrange
        Produto produto = ProdutoHelper
                .gerarProdutoCompleto(1, "Big Mac", "Descricao Big Mac", Categoria.LANCHE, new BigDecimal(10), 50);

        Mockito.when(produtoRepository.listarProdutoPorId(produto.getId())).thenReturn(produto);

        //Act
        Assertions.assertThatThrownBy(() ->
                subtracaoEstoqueProduto.subtrair(produto.getId(), 100))
                .isInstanceOf(EstoqueException.class)
                .hasMessageContaining("Estoque não pode ser negativo");
    }

}