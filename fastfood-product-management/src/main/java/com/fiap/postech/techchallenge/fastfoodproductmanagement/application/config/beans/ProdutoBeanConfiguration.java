package com.fiap.postech.techchallenge.fastfoodproductmanagement.application.config.beans;

import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.ProdutoRepository;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto.*;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto.estoque.AtualizacaoEstoqueProduto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto.estoque.CadastroEstoqueProduto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto.estoque.ListagemEstoqueProduto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto.estoque.SubtracaoEstoqueProduto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto.preco.AtualizacaoPrecoProduto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto.preco.CadastroPrecoProduto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto.preco.ListagemPrecoProduto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProdutoBeanConfiguration {

    private final ProdutoRepository produtoRepository;

    public ProdutoBeanConfiguration(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    @Bean
    public CadastroDeProduto cadastroDeProduto(){
        return new CadastroDeProduto(produtoRepository);
    }

    @Bean
    public ListagemDeProdutos listagemDeProdutos(){
        return new ListagemDeProdutos(produtoRepository);
    }

    @Bean
    public ListagemDeProdutosPorCategoria listagemDeProdutoPorCategoria(){
    return new ListagemDeProdutosPorCategoria(produtoRepository);
    }

    @Bean
    public ListagemDeProdutoPorId listagemDeProdutoPorId(){
        return new ListagemDeProdutoPorId(produtoRepository);
    }
    @Bean
    public AtualizacaoDeProduto atualizacaoDeProduto(){
        return new AtualizacaoDeProduto(produtoRepository);
    }

    @Bean
    public ExclusaoDeProduto exclusaoDeProduto(){
        return new ExclusaoDeProduto(produtoRepository);
    }

    @Bean
    public CadastroEstoqueProduto cadastroEstoqueProduto(){
        return new CadastroEstoqueProduto(produtoRepository);
    }

    @Bean
    public SubtracaoEstoqueProduto subtraiEstoqueProduto(){
        return new SubtracaoEstoqueProduto(produtoRepository);
    }

    @Bean
    public AtualizacaoEstoqueProduto atualizacaoEstoqueProduto(){
        return new AtualizacaoEstoqueProduto(produtoRepository);
    }

    @Bean
    public ListagemEstoqueProduto listagemEstoqueProduto(){
        return new ListagemEstoqueProduto(produtoRepository);
    }

    @Bean
    public CadastroPrecoProduto cadastroPrecoProduto(){
        return new CadastroPrecoProduto(produtoRepository);
    }

    @Bean
    public AtualizacaoPrecoProduto atualizacaoPrecoProduto(){
        return new AtualizacaoPrecoProduto(produtoRepository);
    }

    @Bean
    public ListagemPrecoProduto listagemPrecoProduto(){
        return new ListagemPrecoProduto(produtoRepository);
    }
}
