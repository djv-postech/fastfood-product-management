package com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto.preco;

import com.fiap.postech.techchallenge.fastfoodproductmanagement.application.api.produto.records.DadosCadastroPrecificacaoProduto;
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
class CadastroPrecoProdutoTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private CadastroPrecoProduto cadastroPrecoProduto;


    @Test
    public void devePermitirCadastrarPrecoDeUmProdutoDoCatalogo(){
        //Arrange
        Produto produto = ProdutoHelper
                .gerarProdutoCompleto(1, "Big Mac", "Descricao Big Mac", Categoria.LANCHE, new BigDecimal(0), 0);
        DadosCadastroPrecificacaoProduto dadosCadastroPrecificacaoProduto = ProdutoHelper.gerarDadosCadastroPrecificacaoProduto();

        when(produtoRepository.listarProdutoPorId(any(Integer.class))).thenReturn(produto);

        when(produtoRepository.cadastrarPrecoProduto(produto)).thenAnswer(answer ->answer.getArgument(0));

        //Act
        Produto produtoPreco = cadastroPrecoProduto.cadastrar(dadosCadastroPrecificacaoProduto);

        //Assert
        Assertions.assertThat(produtoPreco).isNotNull();
        Assertions.assertThat(produtoPreco.getPreco()).isEqualTo(BigDecimal.TEN);

    }

    @Test
    public void deveLancarExcecaoAoTentarCadastrarPrecoDeUmProdutoInexistenteNoCatalogo(){
        //Arrange
        Produto produto = ProdutoHelper
                .gerarProdutoCompleto(1, "Big Mac", "Descricao Big Mac", Categoria.LANCHE, new BigDecimal(0), 0);
        DadosCadastroPrecificacaoProduto dadosCadastroPrecificacaoProduto = ProdutoHelper.gerarDadosCadastroPrecificacaoProdutoComPrecoNegativo();

        when(produtoRepository.listarProdutoPorId(any(Integer.class))).thenReturn(null);

        //Act
        Assertions.assertThatThrownBy(() -> cadastroPrecoProduto.cadastrar(dadosCadastroPrecificacaoProduto))
                .isInstanceOf(ProdutoNotFoundException.class)
                .hasMessageContaining("Produto de id 1 não encontrado no catálogo");

    }

    @Test
    public void deveLancarExcecaoAoTentarCadastrarPrecoNegativoEmUmProdutoDoCatalogo(){
        //Arrange
        Produto produto = ProdutoHelper
                .gerarProdutoCompleto(1, "Big Mac", "Descricao Big Mac", Categoria.LANCHE, new BigDecimal(0), 0);
        DadosCadastroPrecificacaoProduto dadosCadastroPrecificacaoProduto = ProdutoHelper.gerarDadosCadastroPrecificacaoProdutoComPrecoNegativo();

        when(produtoRepository.listarProdutoPorId(any(Integer.class))).thenReturn(produto);

        //Act
        Assertions.assertThatThrownBy(() -> cadastroPrecoProduto.cadastrar(dadosCadastroPrecificacaoProduto))
                .isInstanceOf(PrecoException.class)
                .hasMessageContaining("O preço do produto não pode ser negativo");

    }

}