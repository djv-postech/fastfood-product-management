package com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto;

import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Categoria;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Produto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AtualizacaoDeProdutoTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private AtualizacaoDeProduto atualizacaoDeProduto;


    @Test
    public void devePermitirAtualizarAsInformacoesDeUmProdutoNoCatalogo(){
        //Arrage
        Produto produto = new Produto("Mc Flury", "Sorvete", Categoria.SOBREMESA);

        when(produtoRepository.listarProdutoPorId(Mockito.any(Integer.class)))
                .thenReturn(new Produto("Mc Churros", "Churros de Chocolate", Categoria.SOBREMESA));

        when(produtoRepository.atualizarProduto(Mockito.any(Produto.class))).thenAnswer(answer -> answer.getArgument(0));

        //Act
        Produto produtoAtualizado = atualizacaoDeProduto.atualizarDadosProduto(1, produto);

        assertThat(produtoAtualizado).isInstanceOf(Produto.class).isNotNull();
        assertThat(produto.getNome()).isEqualTo(produtoAtualizado.getNome());
        assertThat(produto.getDescricao()).isEqualTo(produtoAtualizado.getDescricao());
        assertThat(produto.getCategoria()).isEqualTo(produtoAtualizado.getCategoria());

    }


}