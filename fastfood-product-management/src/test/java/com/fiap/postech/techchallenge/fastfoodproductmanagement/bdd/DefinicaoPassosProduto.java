package com.fiap.postech.techchallenge.fastfoodproductmanagement.bdd;

import com.fiap.postech.techchallenge.fastfoodproductmanagement.application.api.produto.records.DadosCadastroEstoqueProduto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.application.api.produto.records.DadosCadastroProduto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.application.api.produto.records.DadosEstoqueProduto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.application.api.produto.records.DadosProduto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Categoria;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto.ProdutoHelper;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class DefinicaoPassosProduto {

    private Response response;
    private DadosEstoqueProduto dadosEstoqueProduto;
    private DadosProduto dadosProduto;

    private final String ENDPOINT_API_PRODUTO = "http://localhost:8080/produto";
    private final String ENDPOINT_API_ESTOQUE_PRODUTO = "http://localhost:8080/estoque";


    @Quando("cadastrar um novo produto")
    public DadosProduto cadastrar_um_novo_produto() {

        DadosCadastroProduto dadosCadastroProduto = ProdutoHelper.gerarDadosCadastroProduto();

        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(dadosCadastroProduto)
                .when()
                .post(ENDPOINT_API_PRODUTO);

        return response.then().extract().as(DadosProduto.class);
    }

    @Entao("o produto é cadastrado no catalogo com sucesso")
    public void o_produto_é_cadastrado_no_catalogo_com_sucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("schemas/dados_produto.schema.json"));
    }

    @Entao("deve ser apresentado")
    public void deve_ser_apresentado() {
        response.then()
                .body(matchesJsonSchemaInClasspath("schemas/dados_produto.schema.json"));
    }

    @Dado("que um produto já foi cadastrado no catalogo")
    public void que_um_produto_já_foi_cadastrado_no_catalogo() {
        dadosProduto = cadastrar_um_novo_produto();
    }
    @Quando("quando buscar o produto")
    public void quando_buscar_o_produto() {
       response = when()
               .get(ENDPOINT_API_PRODUTO + "/{id}", dadosProduto.id());
    }
    @Entao("o produto deve ser exibido com sucesso")
    public void o_produto_deve_ser_exibido_com_sucesso() {
        response.then()
                .body(matchesJsonSchemaInClasspath("schemas/dados_produto.schema.json"));
    }

    @Quando("efetuar requisicao para alterar um produto")
    public void efetuar_requisicao_para_alterar_um_produto() {
          response =
                  given()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(new DadosCadastroProduto("Mc Chicken", "Lanche de Frango", Categoria.LANCHE))
                  .when()
                    .put(ENDPOINT_API_PRODUTO + "/{id}", dadosProduto.id());
    }
    @Entao("o produto é atualizado com sucesso")
    public void o_produto_é_atualizado_com_sucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value());
    }

    @Quando("requisitar a remocao do produto")
    public void requisitar_a_remocao_do_produto() {
        when()
                .delete(ENDPOINT_API_PRODUTO + "/{id}", dadosProduto.id());
    }
    @Entao("o produto é removido do catalogo com sucesso")
    public void o_produto_é_removido_do_catalogo_com_sucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value());
    }

    @Quando("cadastrar estoque do produto")
    public DadosEstoqueProduto cadastrar_estoque_do_produto() {
        DadosCadastroEstoqueProduto cadastroEstoqueProduto = ProdutoHelper
                .gerarDadosCadastroEstoqueProduto();

        response = given()
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .body(cadastroEstoqueProduto)
                   .when()
                        .post(ENDPOINT_API_ESTOQUE_PRODUTO);

        return response.then().extract().as(DadosEstoqueProduto.class);
    }
    @Entao("o estoque do produto é cadastrado com sucesso")
    public void o_estoque_do_produto_é_cadastrado_com_sucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value());

    }
    @Entao("a informacao de estoque deve ser apresentada")
    public void a_informacao_de_estoque_deve_ser_apresentada() {
        response.then()
                .body(matchesJsonSchemaInClasspath("schemas/estoque_produto.schema.json"));
    }

    @Dado("que o estoque já foi cadastrado no produto do catalogo")
    public void que_o_estoque_já_foi_cadastrado_no_produto_do_catalogo() {
        dadosEstoqueProduto = cadastrar_estoque_do_produto();

    }
    @Quando("subrair um valor do estoque do produto")
    public void subrair_um_valor_do_estoque_do_produto() {

       response = given()
                  .queryParam("quantidade", 1)
               .when()
                  .post(ENDPOINT_API_ESTOQUE_PRODUTO + "/produto/{id}", dadosEstoqueProduto.idProduto());
    }
    @Entao("o valor é subtraido do estoque com sucesso")
    public void o_valor_é_subtraido_do_estoque_com_sucesso() {
       response.then()
               .statusCode(HttpStatus.OK.value());
    }

    @Quando("requisitar atualizacao de estoque de um produto")
    public void requisitar_atualizacao_de_estoque_de_um_produto() {
        given()
                .queryParam("quantidade", 200)

        .when()
                .put(ENDPOINT_API_ESTOQUE_PRODUTO + "/produto/{id}", dadosEstoqueProduto.idProduto());

    }
    @Entao("o estoque do produto é atualizado com sucesso")
    public void o_estoque_do_produto_é_atualizado_com_sucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value());
    }
}
