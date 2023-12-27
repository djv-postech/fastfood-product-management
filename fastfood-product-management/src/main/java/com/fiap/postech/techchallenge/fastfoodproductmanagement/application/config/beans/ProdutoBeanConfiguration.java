package com.fiap.postech.techchallenge.fastfoodproductmanagement.application.config.beans;

import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.ProdutoRepository;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto.*;
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

}
