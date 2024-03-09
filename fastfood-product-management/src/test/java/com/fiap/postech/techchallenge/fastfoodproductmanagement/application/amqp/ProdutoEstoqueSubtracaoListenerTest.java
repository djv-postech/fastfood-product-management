package com.fiap.postech.techchallenge.fastfoodproductmanagement.application.amqp;

import com.fiap.postech.techchallenge.fastfoodproductmanagement.application.api.produto.records.DadosSubtracaoEstoqueProduto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto.ProdutoHelper;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto.estoque.SubtracaoEstoqueProduto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class ProdutoEstoqueSubtracaoListenerTest {

    @Mock
    private SubtracaoEstoqueProduto subtracaoEstoqueProduto;

    @InjectMocks
    private ProdutoEstoqueSubtracaoListener produtoEstoqueSubtracaoListener;


    @Test
    public void deveSubtrairEstoqueDosProdutos(){
        List<DadosSubtracaoEstoqueProduto> dadosSubtracaoEstoqueProduto = ProdutoHelper.gerarDadosSubtracaoEstoqueProduto();

        Mockito.doNothing().when(subtracaoEstoqueProduto).subtrairEstoqueListaProdutos(dadosSubtracaoEstoqueProduto);

        produtoEstoqueSubtracaoListener.subtrairEstoque(dadosSubtracaoEstoqueProduto);

        Mockito.verify(subtracaoEstoqueProduto, Mockito.times(1)).subtrairEstoqueListaProdutos(dadosSubtracaoEstoqueProduto);
    }

}