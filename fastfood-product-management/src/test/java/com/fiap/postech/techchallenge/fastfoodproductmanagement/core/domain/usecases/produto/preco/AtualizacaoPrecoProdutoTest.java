package com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto.preco;

import com.fiap.postech.techchallenge.fastfoodproductmanagement.application.api.produto.records.DadosAtualizacaoPrecificacaoProduto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Categoria;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Produto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.ProdutoRepository;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.exception.PrecoException;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.exception.ProdutoNotFoundException;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto.ProdutoHelper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AtualizacaoPrecoProdutoTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private AtualizacaoPrecoProduto atualizacaoPrecoProduto;

    @Test
    public void devePermitirAtualizarPrecoDeUmProdutoDoCatalogo(){
        //Arrange
        Produto produto = ProdutoHelper
                .gerarProdutoCompleto(1, "Big Mac", "Descricao Big Mac", Categoria.LANCHE, new BigDecimal(0), 0);
        DadosAtualizacaoPrecificacaoProduto dadosAtualizacaoPrecificacaoProduto = ProdutoHelper.gerarDadosAtualizacaoPrecoProduto();

        when(produtoRepository.listarProdutoPorId(any(Integer.class))).thenReturn(produto);

        when(produtoRepository.cadastrarPrecoProduto(produto)).thenAnswer(answer ->answer.getArgument(0));

        //Act
        Produto produtoPreco = atualizacaoPrecoProduto.atualizar(produto.getId(), dadosAtualizacaoPrecificacaoProduto);

        //Assert
        Assertions.assertThat(produtoPreco).isNotNull();
        Assertions.assertThat(produtoPreco.getPreco()).isEqualTo(new BigDecimal(500));

    }

    @Test
    public void deveLancarExcecaoAoTentarAtualizarPrecoDeUmProdutoInexistenteNoCatalogo(){
        //Arrange
        Produto produto = ProdutoHelper
                .gerarProdutoCompleto(1, "Big Mac", "Descricao Big Mac", Categoria.LANCHE, new BigDecimal(0), 0);
        DadosAtualizacaoPrecificacaoProduto dadosAtualizacaoPrecificacaoProduto = ProdutoHelper.gerarDadosAtualizacaoPrecoProduto();

        when(produtoRepository.listarProdutoPorId(any(Integer.class))).thenReturn(null);

        //Act
        Assertions.assertThatThrownBy(() -> atualizacaoPrecoProduto.atualizar(produto.getId(), dadosAtualizacaoPrecificacaoProduto))
                .isInstanceOf(ProdutoNotFoundException.class)
                .hasMessageContaining("Produto de id 1 não encontrado no catálogo");

    }

    @Test
    public void deveLancarExcecaoAoTentarAtualizarProdutoDoCatalogoComPrecoNegativo(){
        //Arrange
        Produto produto = ProdutoHelper
                .gerarProdutoCompleto(1, "Big Mac", "Descricao Big Mac", Categoria.LANCHE, new BigDecimal(0), 0);
        DadosAtualizacaoPrecificacaoProduto dadosAtualizacaoPrecificacaoProduto = ProdutoHelper.gerarDadosAtualizacaoPrecoProdutoComPrecoNegativo();

        when(produtoRepository.listarProdutoPorId(any(Integer.class))).thenReturn(produto);

        //Act
        Assertions.assertThatThrownBy(() -> atualizacaoPrecoProduto.atualizar(produto.getId(), dadosAtualizacaoPrecificacaoProduto))
                .isInstanceOf(PrecoException.class)
                .hasMessageContaining("O preço do produto não pode ser negativo");

    }


}