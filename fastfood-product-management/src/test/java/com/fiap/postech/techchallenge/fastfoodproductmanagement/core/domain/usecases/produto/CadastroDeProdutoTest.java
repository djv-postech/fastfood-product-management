package com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto;

import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.ProdutoRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class CadastroDeProdutoTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private CadastroDeProduto cadastroDeProduto;



}