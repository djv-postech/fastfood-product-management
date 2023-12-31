package com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto;

import com.fiap.postech.techchallenge.fastfoodproductmanagement.application.api.produto.records.DadosAtualizacaoPrecificacaoProduto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.application.api.produto.records.DadosCadastroEstoqueProduto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.application.api.produto.records.DadosCadastroPrecificacaoProduto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.application.api.produto.records.DadosCadastroProduto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Categoria;

import java.math.BigDecimal;

public class ProdutoHelper {

    public static DadosCadastroProduto gerarDadosCadastroProduto() {
        return new DadosCadastroProduto("Big Mac", "2 hamburgueres e alface", Categoria.LANCHE);
    }

    public static DadosCadastroEstoqueProduto gerarDadosCadastroEstoqueProduto() {
        return new DadosCadastroEstoqueProduto(1, 10);
    }

    public static DadosCadastroPrecificacaoProduto gerarDadosCadastroPrecificacaoProduto() {
        return new DadosCadastroPrecificacaoProduto(1, BigDecimal.TEN);
    }

    public static DadosAtualizacaoPrecificacaoProduto gerarDadosAtualizacaoPrecoProduto() {
        return new DadosAtualizacaoPrecificacaoProduto(new BigDecimal(500));
    }
}
