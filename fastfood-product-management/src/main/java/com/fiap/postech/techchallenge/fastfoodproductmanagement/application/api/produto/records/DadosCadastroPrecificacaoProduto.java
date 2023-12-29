package com.fiap.postech.techchallenge.fastfoodproductmanagement.application.api.produto.records;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record DadosCadastroPrecificacaoProduto(

            @NotBlank
            Integer idProduto,

            @NotBlank
            BigDecimal preco){
}
