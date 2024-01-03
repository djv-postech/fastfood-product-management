package com.fiap.postech.techchallenge.fastfoodproductmanagement.application.api.produto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.application.api.produto.records.DadosCadastroProduto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Categoria;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Produto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.exception.ProdutoNotFoundException;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ProdutoControllerTest {

    @Mock
    private CadastroDeProduto cadastrarProduto;

    @Mock
    private ListagemDeProdutos listagemDeProdutos;

    @Mock
    private ListagemDeProdutoPorId listagemDeProdutoPorId;

    @Mock
    private ListagemDeProdutosPorCategoria listagemDeProdutosPorCategoria;

    @Mock
    private AtualizacaoDeProduto atualizacaoDeProduto;

    @Mock
    private ExclusaoDeProduto exclusaoDeProduto;

    private MockMvc mockMvc;

    private ProdutoController produtoController;

    @BeforeEach
    public void init() {
        produtoController = new ProdutoController(cadastrarProduto, listagemDeProdutos, listagemDeProdutoPorId,
                listagemDeProdutosPorCategoria, atualizacaoDeProduto, exclusaoDeProduto);
        this.mockMvc = MockMvcBuilders.standaloneSetup(produtoController).build();
    }

    @DisplayName("Test - Deve permitir cadastrar produto no catálogo")
    @Test
    public void devePermitirCadastrarProduto() throws Exception {
        // Dado
        DadosCadastroProduto dadosCadastroProduto = new DadosCadastroProduto("big mac", "pao, hamburguer e queijo", Categoria.LANCHE);

        given(cadastrarProduto.cadastrar(any(Produto.class))).willAnswer(answer -> answer.getArgument(0));

        // Quando
         mockMvc.perform(post("/produto")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertToJson(dadosCadastroProduto)))
                .andExpect(status().isCreated());

        verify(cadastrarProduto, times(1)).cadastrar(any(Produto.class));

    }

    @DisplayName("Test - Lista todos os produtos")
    @Test
    public void devePermitirConsultarTodosProdutosCadastradosNoCatalogo() throws Exception {
        // Dado
        Produto produto1 = ProdutoHelper
                .gerarProdutoCompleto(1, "Big Mac", "Descricao Big Mac", Categoria.LANCHE, new BigDecimal(5), 10);

        Produto produto2 = ProdutoHelper
                .gerarProdutoCompleto(2, "Big Tasty", "Descricao Big Mac", Categoria.LANCHE, new BigDecimal(4), 20);


        given(listagemDeProdutos.listarTodosOsProdutos()).willReturn(List.of(produto1,produto2));

        // Quando
        mockMvc
                .perform(
                        get("/produto/todos").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @DisplayName("Test - Lista produto por ID")
    @Test
    public void deveRetornarProdutoDoCatalogoQuandoConsultarPorId() throws Exception {
        // Dado
        Produto produto = ProdutoHelper
                .gerarProdutoCompleto(1, "Big Mac", "Descricao Big Mac", Categoria.LANCHE, new BigDecimal(5), 10);

        given(listagemDeProdutoPorId.listarProdutoPorId(1)).willReturn(produto);

        // Quando
        mockMvc
            .perform(
                    get("/produto/1")
                            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @DisplayName("Test - Deve lançar exceção ao consultar por produto inexistente no catáogo")
    @Test
    public void deveLancarExcecaoAoConsultarPorIdDeProdutoInexistenteNoCatalogo() throws Exception {
        // Dado
        Produto produto = ProdutoHelper
                .gerarProdutoCompleto(1, "Big Mac", "Descricao Big Mac", Categoria.LANCHE, new BigDecimal(5), 10);

        given(listagemDeProdutoPorId.listarProdutoPorId(produto.getId())).willThrow(ProdutoNotFoundException.class);

        // Quando
        mockMvc
                .perform(
                        get("/produto/1")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @DisplayName("Test - Lista todos os produtos de uma categoria")
    @Test
    public void deveRetornarTodosOsProdutosDeUmaCategoria() throws Exception {
        // Dado
        Produto produto1 = ProdutoHelper
                .gerarProdutoCompleto(1, "Big Mac", "Descricao Big Mac", Categoria.LANCHE, new BigDecimal(5), 10);

        Produto produto2 = ProdutoHelper
                .gerarProdutoCompleto(2, "Big Tasty", "Descricao Big Mac", Categoria.LANCHE, new BigDecimal(4), 20);

        //Dado
        given(listagemDeProdutosPorCategoria.listaProdutosPorCategoria(Categoria.LANCHE))
                .willReturn(List.of(produto1,produto2));

        // Quando

        mockMvc
                .perform(
                        get("/produto").param("categoria", "LANCHE")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @DisplayName("Test - Quando consulta por categoria e nao existir produto, entao deve retornar lista vazia")
    @Test
    public void quandoConsultarPorCategoriaENaoExistirProdutoEntaoDeveRetornarListaVazia() throws Exception {
        //Dado
        given(listagemDeProdutosPorCategoria.listaProdutosPorCategoria(Categoria.LANCHE)).willReturn(null);

        mockMvc
            .perform(
                    get("/produto").param("categoria", "LANCHE")
                            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    @DisplayName("Test - Remove produto por ID")
    @Test
    public void deveRemoverProdutoPorId() throws Exception {
        //Dado
        doNothing().when(exclusaoDeProduto).removerProdutoDoCatalogo(1);


        mockMvc
                .perform(
                        delete("/produto/1")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    public static String convertToJson(Object object) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(object);
    }
}