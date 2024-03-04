package com.fiap.postech.techchallenge.fastfoodproductmanagement.application.amqp;

import com.fiap.postech.techchallenge.fastfoodproductmanagement.application.api.produto.records.DadosSubtracaoEstoqueProduto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto.estoque.SubtracaoEstoqueProduto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.fiap.postech.techchallenge.fastfoodproductmanagement.infra.config.amqp.ProdutoAMQPConfiguration.PRODUTO_ESTOQUE_SUBTRACAO_QUEUE;

@Component
public class ProdutoEstoqueSubtracaoListener {

    private final SubtracaoEstoqueProduto subtracaoEstoqueProduto;

    public ProdutoEstoqueSubtracaoListener(SubtracaoEstoqueProduto subtracaoEstoqueProduto) {
        this.subtracaoEstoqueProduto = subtracaoEstoqueProduto;
    }

    @RabbitListener(queues = PRODUTO_ESTOQUE_SUBTRACAO_QUEUE)
    public void subtrairEstoque(List<DadosSubtracaoEstoqueProduto> dadosSubtracaoEstoqueProdutos){
        subtracaoEstoqueProduto.subtrairEstoqueListaProdutos(dadosSubtracaoEstoqueProdutos);
    }
}
