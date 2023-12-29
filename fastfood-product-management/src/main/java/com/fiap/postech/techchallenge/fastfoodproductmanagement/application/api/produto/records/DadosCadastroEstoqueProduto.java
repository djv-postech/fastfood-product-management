package com.fiap.postech.techchallenge.fastfoodproductmanagement.application.api.produto.records;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroEstoqueProduto(

            @NotBlank
            Integer idProduto,

            @NotBlank
            Integer quantidadeEstoque){
}
