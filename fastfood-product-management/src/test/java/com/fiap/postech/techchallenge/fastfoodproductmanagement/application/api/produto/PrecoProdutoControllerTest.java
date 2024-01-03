package com.fiap.postech.techchallenge.fastfoodproductmanagement.application.api.produto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.application.api.produto.records.DadosAtualizacaoPrecificacaoProduto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.application.api.produto.records.DadosCadastroEstoqueProduto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.application.api.produto.records.DadosCadastroPrecificacaoProduto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Categoria;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Produto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto.ProdutoHelper;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto.preco.AtualizacaoPrecoProduto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto.preco.CadastroPrecoProduto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto.preco.ListagemPrecoProduto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class PrecoProdutoControllerTest {

    @Mock
    private CadastroPrecoProduto cadastroPrecoProduto;

    @Mock
    private AtualizacaoPrecoProduto atualizacaoPrecoProduto;

    @Mock
    private ListagemPrecoProduto listagemPrecoProduto;

    private MockMvc mockMvc;

    private PrecoProdutoController precoProdutoController;


    @BeforeEach
    public void init() {
        precoProdutoController = new PrecoProdutoController(cadastroPrecoProduto, atualizacaoPrecoProduto,
                listagemPrecoProduto);
        this.mockMvc = MockMvcBuilders.standaloneSetup(precoProdutoController).build();
    }

    @DisplayName("Test - Deve permitir cadastrar preço de produto do catálogo")
    @Test
    public void devePermitirCadastrarPreçoDeProdutoDoCatalogo() throws Exception {
        // Dado
        Produto produto = ProdutoHelper
                .gerarProduto( "Big Mac", "Descricao Big Mac", Categoria.LANCHE);

        DadosCadastroPrecificacaoProduto dadosCadastroPrecificacaoProduto = ProdutoHelper
                .gerarDadosCadastroPrecificacaoProduto();

        given(cadastroPrecoProduto.cadastrar(dadosCadastroPrecificacaoProduto))
                .willReturn(produto);

        // Quando
        mockMvc.perform(post("/preco")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertToJson(dadosCadastroPrecificacaoProduto)))
                .andExpect(status().isCreated());

        verify(cadastroPrecoProduto, times(1))
                .cadastrar(any(DadosCadastroPrecificacaoProduto.class));

    }

    @DisplayName("Test - Deve permitir cadastrar preço de produto do catálogo")
    @Test
    public void devePermitirAtualizarPreçoDeProdutoDoCatalogo() throws Exception {
        // Dado
        Produto produto = ProdutoHelper
                .gerarProduto( "Big Mac", "Descricao Big Mac", Categoria.LANCHE);

        DadosAtualizacaoPrecificacaoProduto dadosAtualizacaoPrecificacaoProduto = ProdutoHelper
                .gerarDadosAtualizacaoPrecoProduto();

        given(atualizacaoPrecoProduto.atualizar(1, dadosAtualizacaoPrecificacaoProduto))
                .willReturn(produto);

        // Quando
        mockMvc.perform(put("/preco/produto/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertToJson(dadosAtualizacaoPrecificacaoProduto)))
                .andExpect(status().isAccepted());

        verify(atualizacaoPrecoProduto, times(1))
                .atualizar(any(Integer.class), any(DadosAtualizacaoPrecificacaoProduto.class));

    }

    @DisplayName("Test - Deve permitir listar o preço de  todos os produtos do catálogo")
    @Test
    public void devePermitirListarOsPrecosDeTodosOsProdutosDoCatalogo() throws Exception {
        // Dado
        Produto produto = ProdutoHelper
                .gerarProdutoCompleto( 1,"Big Mac", "Descricao Big Mac", Categoria.LANCHE, new BigDecimal(10), 10);

        given(listagemPrecoProduto.listarPrecoProdutos())
                .willReturn(Collections.singletonList(produto));

        // Quando
        mockMvc.perform(MockMvcRequestBuilders.get("/preco/produto/todos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(listagemPrecoProduto, times(1))
                .listarPrecoProdutos();

    }

    @DisplayName("Test - Deve permitir buscar o preço de um produto do catálogo")
    @Test
    public void devePermitirBuscarOPrecoDeUmProdutoDoCatalogo() throws Exception {
        // Dado
        Produto produto = ProdutoHelper
                .gerarProdutoCompleto( 1,"Big Mac", "Descricao Big Mac", Categoria.LANCHE, new BigDecimal(10), 10);

        given(listagemPrecoProduto.listarPrecoDoProdutoPorId(1))
                .willReturn(produto);

        // Quando
        mockMvc.perform(MockMvcRequestBuilders.get("/preco/produto/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(listagemPrecoProduto, times(1))
                .listarPrecoDoProdutoPorId(any(Integer.class));

    }

    public static String convertToJson(Object object) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(object);
    }

}