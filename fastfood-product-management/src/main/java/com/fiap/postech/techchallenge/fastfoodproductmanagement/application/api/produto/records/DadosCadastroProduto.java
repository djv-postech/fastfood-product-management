package com.fiap.postech.techchallenge.fastfoodproductmanagement.application.api.produto.records;

import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Categoria;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Produto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DadosCadastroProduto(

        @NotBlank
        String nome,

        @NotBlank
        String descricao,

        @NotNull
        Categoria categoria)  {

        public List<Produto> convertToProduto(DadosCadastroProduto dadosCadastroProduto){
                return null;
        }
}
