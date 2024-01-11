package com.fiap.postech.techchallenge.fastfoodproductmanagement.application.api.produto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.application.api.produto.records.DadosCadastroEstoqueProduto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.application.api.produto.records.DadosSubtracaoEstoqueProduto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Categoria;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Produto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto.ProdutoHelper;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto.estoque.AtualizacaoEstoqueProduto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto.estoque.CadastroEstoqueProduto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto.estoque.ListagemEstoqueProduto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto.estoque.SubtracaoEstoqueProduto;

import org.hamcrest.Matchers;
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
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class EstoqueProdutoControllerTest {

    @Mock
    private CadastroEstoqueProduto cadastroEstoqueProduto;

    @Mock
    private ListagemEstoqueProduto listagemEstoqueProduto;

    @Mock
    private SubtracaoEstoqueProduto subtracaoEstoqueProduto;

    @Mock
    private AtualizacaoEstoqueProduto atualizacaoEstoqueProduto;

    private MockMvc mockMvc;

    private EstoqueProdutoController estoqueProdutoController;


    @BeforeEach
    public void init() {
        estoqueProdutoController = new EstoqueProdutoController(cadastroEstoqueProduto, subtracaoEstoqueProduto,
                atualizacaoEstoqueProduto, listagemEstoqueProduto);
        this.mockMvc = MockMvcBuilders.standaloneSetup(estoqueProdutoController).build();
    }

    @DisplayName("Test - Deve permitir cadastrar estoque de produto do catálogo")
    @Test
    public void devePermitirCadastrarEstoqueDeProdutoDoCatalogo() throws Exception {
        // Dado
        Produto produto = ProdutoHelper
                .gerarProduto( "Big Mac", "Descricao Big Mac", Categoria.LANCHE);

        DadosCadastroEstoqueProduto dadosCadastroEstoqueProduto = ProdutoHelper
                .gerarDadosCadastroEstoqueProduto();

        given(cadastroEstoqueProduto.cadastrar(dadosCadastroEstoqueProduto.idProduto(), dadosCadastroEstoqueProduto.quantidadeEstoque()))
                .willReturn(produto);

        // Quando
        mockMvc.perform(post("/estoque")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertToJson(dadosCadastroEstoqueProduto)))
                .andExpect(status().isCreated());

        verify(cadastroEstoqueProduto, times(1))
                .cadastrar(any(Integer.class), any(Integer.class));

    }

    @DisplayName("Test - Deve permitir subtrair estoque de produto do catálogo")
    @Test
    public void devePermitirSubtrairEstoqueDeUmProdutoDoCatalogo() throws Exception {
        // Dado
        Produto produto = ProdutoHelper
                .gerarProduto( "Big Mac", "Descricao Big Mac", Categoria.LANCHE);

        given(subtracaoEstoqueProduto.subtrair(1, 10)).willReturn(produto);

        // Quando
        mockMvc.perform(post("/estoque/produto/1")
                .queryParam("quantidade", "10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(subtracaoEstoqueProduto, times(1))
                .subtrair(any(Integer.class), any(Integer.class));

    }

    @DisplayName("Test - Deve permitir subtrair estoque de produto do catálogo")
    @Test
    public void devePermitirSubtrairEstoqueDeUmaListaDeProdutosDoCatalogo() throws Exception {
        // Dado
        DadosSubtracaoEstoqueProduto dadosSubtracaoEstoqueProduto1 = new DadosSubtracaoEstoqueProduto(1, 1);
        DadosSubtracaoEstoqueProduto dadosSubtracaoEstoqueProduto2 = new DadosSubtracaoEstoqueProduto(2, 1);

        List<DadosSubtracaoEstoqueProduto> dadosSubtracaoEstoqueProdutoList = new ArrayList<>();
        dadosSubtracaoEstoqueProdutoList.add(dadosSubtracaoEstoqueProduto1);
        dadosSubtracaoEstoqueProdutoList.add(dadosSubtracaoEstoqueProduto2);

         doNothing().when(subtracaoEstoqueProduto).subtrairEstoqueListaProdutos(dadosSubtracaoEstoqueProdutoList);

         // Quando
         mockMvc.perform(post("/estoque/produto")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertToJson(dadosSubtracaoEstoqueProdutoList)))
                .andExpect(status().isOk());

        verify(subtracaoEstoqueProduto, times(1))
                .subtrairEstoqueListaProdutos(any(List.class));

    }

    @DisplayName("Test - Deve permitir atualizar estoque de produto do catálogo")
    @Test
    public void devePermitirAtualizarEstoqueDeProdutoDoCatalogo() throws Exception {
        // Dado
        Produto produto = ProdutoHelper
                .gerarProduto( "Big Mac", "Descricao Big Mac", Categoria.LANCHE);


        given(atualizacaoEstoqueProduto.atualizar(1, 5)).willReturn(produto);

        // Quando
        mockMvc.perform(put("/estoque/produto/1")
                .contentType(MediaType.APPLICATION_JSON)
                .queryParam("quantidade", "5"))
                .andExpect(status().isAccepted());

        verify(atualizacaoEstoqueProduto, times(1))
                .atualizar(any(Integer.class), any(Integer.class));

    }

    @DisplayName("Test - Deve permitir listar estoque de todos os produto do catálogo")
    @Test
    public void devePermitirListarEstoqueDeTodosOsProdutosDoCatalogo() throws Exception {
        // Dado
        Produto produto1 = ProdutoHelper
                .gerarProdutoCompleto(1, "Big Mac", "Descricao Big Mac", Categoria.LANCHE, new BigDecimal(5), 10);

        Produto produto2 = ProdutoHelper
                .gerarProdutoCompleto(2, "Big Tasty", "Descricao Big Mac", Categoria.LANCHE, new BigDecimal(4), 20);



        given(listagemEstoqueProduto.total()).willReturn(Arrays.asList(produto1, produto2));

        // Quando
        mockMvc.perform(MockMvcRequestBuilders.get("/estoque/total")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(listagemEstoqueProduto, times(1))
                .total();

    }

    @DisplayName("Test - Deve permitir listar estoque de todos os produto do catálogo")
    @Test
    public void devePermitirBuscarEstoqueDeProdutosDoCatalogo() throws Exception {
        // Dado
        Produto produto1 = ProdutoHelper
                .gerarProdutoCompleto(1, "Big Mac", "Descricao Big Mac", Categoria.LANCHE, new BigDecimal(5), 10);


        given(listagemEstoqueProduto.listarEstoqueProdutoPorId(any(Integer.class)))
                .willReturn(produto1);

        // Quando
        mockMvc.perform(MockMvcRequestBuilders.get("/estoque/produto/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(listagemEstoqueProduto, times(1))
                .listarEstoqueProdutoPorId(any(Integer.class));

    }


    public static String convertToJson(Object object) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(object);
    }

}