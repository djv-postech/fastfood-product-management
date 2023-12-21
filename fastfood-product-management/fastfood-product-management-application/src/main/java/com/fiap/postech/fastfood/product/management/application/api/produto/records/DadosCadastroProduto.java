package com.fiap.postech.fastfood.product.management.application.api.produto.records;


import com.fiap.postech.fastfood.product.management.core.produto.Categoria;
import com.fiap.postech.fastfood.product.management.core.produto.Produto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;


public record DadosCadastroProduto(
        @NotBlank
        String nome,

        @NotBlank
        String descricao,

        @NotNull
        BigDecimal preco,

        @NotNull
        Categoria categoria)  {

        public List<Produto> convertToProduto(DadosCadastroProduto dadosCadastroProduto){
                return null;
        }
}
