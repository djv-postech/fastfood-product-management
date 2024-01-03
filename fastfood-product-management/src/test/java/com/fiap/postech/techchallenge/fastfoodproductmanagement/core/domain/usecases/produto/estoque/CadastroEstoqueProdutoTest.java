package com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto.estoque;

import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Categoria;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Produto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.ProdutoRepository;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto.ProdutoHelper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CadastroEstoqueProdutoTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private CadastroEstoqueProduto cadastroEstoqueProduto;


    @Test
    public void devePermitirCadastrarEstoqueEmUmProdutoDoCatalogo(){
        //Arrange
        Produto produto = ProdutoHelper.gerarProduto("Big Mac", "descricao big mac", Categoria.LANCHE);

        when(produtoRepository.listarProdutoPorId(any(Integer.class))).thenReturn(produto);

        when(produtoRepository.cadastrarEstoqueProduto(produto)).thenAnswer(answer ->answer.getArgument(0));

        //Act
        Produto produtoEstoque = cadastroEstoqueProduto.cadastrar(1, 10);

        //Assert
        Assertions.assertThat(produtoEstoque).isNotNull();
        Assertions.assertThat(produtoEstoque.getQuantidadeEstoque()).isEqualTo(10);

    }

}