package com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto;

import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.stereotype.Repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExclusaoDeProdutoTest {


    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ExclusaoDeProduto exclusaoDeProduto;

    @Test
    public void devePermitirRemoverUmProdutoDoCatalogo(){

        //Arrange
        doNothing().when(produtoRepository).removerProduto(Mockito.any(Integer.class));

        //Act
        exclusaoDeProduto.removerProdutoDoCatalogo(1);


        //Assert
        verify(produtoRepository, times(1)).removerProduto(any(Integer.class));

    }
}