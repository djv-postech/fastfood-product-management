package com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto;

import com.fiap.postech.techchallenge.fastfoodproductmanagement.application.api.produto.records.*;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Categoria;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Produto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

    public static Produto gerarProduto(String nome, String descricao, Categoria categoria) {
        return new Produto(nome, descricao, categoria);
    }

    public static Produto gerarProdutoCompleto(int id, String nome, String descricao, Categoria categoria, BigDecimal preco, int estoque) {
        Produto produto = new Produto();
        produto.setId(id);
        produto.setNome(nome);
        produto.setDescricao(descricao);
        produto.setCategoria(categoria);
        produto.adicionarEstoque(estoque);
        produto.precificarProduto(preco);

        return produto;
    }

    public static DadosCadastroPrecificacaoProduto gerarDadosCadastroPrecificacaoProdutoComPrecoNegativo() {
        return new DadosCadastroPrecificacaoProduto(1, new BigDecimal(-1));
    }

    public static DadosAtualizacaoPrecificacaoProduto gerarDadosAtualizacaoPrecoProdutoComPrecoNegativo() {
        return new DadosAtualizacaoPrecificacaoProduto(new BigDecimal(-1));
    }

    public static List<DadosSubtracaoEstoqueProduto> gerarDadosSubtracaoEstoqueProduto(){
        List<DadosSubtracaoEstoqueProduto> dadosEstoque = new ArrayList<>();
        dadosEstoque.add(new DadosSubtracaoEstoqueProduto(1, 10));
        return dadosEstoque;
    }
}
