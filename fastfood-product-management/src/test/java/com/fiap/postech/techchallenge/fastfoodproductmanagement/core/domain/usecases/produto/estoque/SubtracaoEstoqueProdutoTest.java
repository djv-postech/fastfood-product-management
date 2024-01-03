package com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto.estoque;

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