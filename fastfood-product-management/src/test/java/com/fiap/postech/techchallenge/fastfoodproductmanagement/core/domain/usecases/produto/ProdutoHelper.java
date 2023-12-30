package com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto;

import com.fiap.postech.techchallenge.fastfoodproductmanagement.application.api.produto.records.DadosCadastroEstoqueProduto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.application.api.produto.records.DadosCadastroProduto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Categoria;

public class ProdutoHelper {

    public static DadosCadastroProduto gerarDadosCadastroProduto() {
        return new DadosCadastroProduto("Big Mac", "2 hamburgueres e alface", Categoria.LANCHE);
    }

    public static DadosCadastroEstoqueProduto gerarDadosCadastroEstoqueProduto() {
        return new DadosCadastroEstoqueProduto(1, 10);
    }
}
