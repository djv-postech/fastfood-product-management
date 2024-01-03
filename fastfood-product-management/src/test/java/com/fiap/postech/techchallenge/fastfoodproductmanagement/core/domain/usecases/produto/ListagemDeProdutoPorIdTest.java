package com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto;

import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Categoria;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Produto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.ProdutoRepository;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.exception.ProdutoNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListagemDeProdutoPorIdTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ListagemDeProdutoPorId listagemDeProdutoPorId;

    @Test
    public void deveRetornarProdutoDoCatalogoAoEfetuarBuscaPorId(){
        //Arrange
        Produto produto = ProdutoHelper.gerarProduto("Big Mac", "2 hamburgueres", Categoria.LANCHE);

        when(produtoRepository.listarProdutoPorId(Mockito.any(Integer.class))).thenReturn(produto);

        //Act
        Produto produtoBuscado = listagemDeProdutoPorId.listarProdutoPorId(1);

        //Assert
        Assertions.assertThat(produtoBuscado).isInstanceOf(Produto.class).isNotNull();
        Assertions.assertThat(produtoBuscado.getNome()).isEqualTo(produto.getNome());
    }

    @Test
    public void deveLancarExcecaoQuandoBuscarPorUmProdutoInexistenteNoCatalogo(){
        //Arrange
        Integer id = 1;
        Produto produto = ProdutoHelper.gerarProduto("Big Mac", "2 hamburgueres", Categoria.LANCHE);

        when(produtoRepository.listarProdutoPorId(Mockito.any(Integer.class))).thenReturn(null);


        assertThatThrownBy(() -> listagemDeProdutoPorId.listarProdutoPorId(id))
                .isInstanceOf(ProdutoNotFoundException.class)
                .hasMessageContaining("Produto de id "  + id + " não encontrado no catálogo.");



    }
}