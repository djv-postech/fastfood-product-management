package com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto;

import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Categoria;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Produto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.ProdutoRepository;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.infra.persistence.repository.ProdutoRepositoryMysql;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.infra.persistence.repository.entity.ProdutoEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class CadastroDeProdutoTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private CadastroDeProduto cadastroDeProduto;


    @Test
    public void devePermitirCadastrarUmProdutoNoCatalogo(){
        //Arrage
        var produto = new Produto("Big Mac", "Dois hamburgueres alface queijo e pao", Categoria.LANCHE);

        Mockito.when(produtoRepository.cadastrarProduto(Mockito.any(Produto.class)))
                .thenAnswer(answer -> answer.getArgument(0));

        //Act
        Produto produtoCadastrado = cadastroDeProduto.cadastrar(produto);

        //Assert
        assertThat(produtoCadastrado).isInstanceOf(Produto.class).isNotNull();
        assertThat(produtoCadastrado).isEqualTo(produto);

    }

}