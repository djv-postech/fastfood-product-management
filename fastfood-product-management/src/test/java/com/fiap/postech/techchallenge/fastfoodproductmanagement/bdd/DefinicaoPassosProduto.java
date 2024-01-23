package com.fiap.postech.techchallenge.fastfoodproductmanagement.bdd;

import com.fiap.postech.techchallenge.fastfoodproductmanagement.application.api.produto.records.*;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Categoria;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto.ProdutoHelper;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class DefinicaoPassosProduto {

    private Response response;
    private DadosEstoqueProduto dadosEstoqueProduto;
    private DadosProduto dadosProduto;

    private final String BASE_URL = "http://localhost:8081";
    private final String ENDPOINT_API_PRODUTO = BASE_URL + "/produto";
    private final String ENDPOINT_API_ESTOQUE_PRODUTO = BASE_URL + "/estoque";
    private final String ENDPOINT_API_PRECIFICACAO_PRODUTO = BASE_URL + "/preco";


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
                .statusCode(HttpStatus.CREATED.value());
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

    @Quando("pesquisar um produto que nao existe no catalogo")
    public void pesquisar_um_produto_que_nao_existe_no_catalogo() {
        response = when()
                .get(ENDPOINT_API_PRODUTO + "/{id}", 200);
    }
    @Entao("uma mensagem de erro deve ser apresentada")
    public void uma_mensagem_de_erro_deve_ser_apresentada() {

        var body = response.then().extract().body().asPrettyString();

       response.then()
               .statusCode(HttpStatus.NOT_FOUND.value());

        Assertions.assertThat(body).isEqualTo("Produto de id 200 não encontrado no catálogo.");


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
                .statusCode(HttpStatus.ACCEPTED.value());
    }

    @Quando("requisitar a remocao do produto")
    public void requisitar_a_remocao_do_produto() {
         response = when()
                .delete(ENDPOINT_API_PRODUTO + "/{id}", dadosProduto.id());
    }
    @Entao("o produto é removido do catalogo com sucesso")
    public void o_produto_é_removido_do_catalogo_com_sucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value());
    }

    @Quando("evefuar a requiscao da listagem de todos os produtos")
    public void evefuar_a_requiscao_da_listagem_de_todos_os_produtos() {
        response =
                when()
                    .get(ENDPOINT_API_PRODUTO + "/todos");
    }
    @Entao("a lista de produtos cadastrados é retornada com sucesso")
    public void a_lista_de_produtos_cadastrados_é_retornada_com_sucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value());
    }
    @Entao("deve ser apresentada")
    public void deve_ser_apresentada() {
        response.then()
                .body(matchesJsonSchemaInClasspath("schemas/lista_dados_produto.schema.json"));
    }

    @Quando("buscar um produto por categoria")
    public void buscar_um_produto_por_categoria() {
       response =
               given()
               .queryParam("categoria", "LANCHE")
               .when()
               .get(ENDPOINT_API_PRODUTO);
    }

    @Entao("a listagem de produtos da categoria é exibida com sucesso")
    public void a_listagem_de_produtos_da_categoria_é_exibida_com_sucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("schemas/lista_dados_produto.schema.json"));
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
                .statusCode(HttpStatus.CREATED.value());

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
    @Quando("subtrair um valor do estoque do produto")
    public void subtrair_um_valor_do_estoque_do_produto() {
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
        response = given()
                .queryParam("quantidade", 200)

        .when()
                .put(ENDPOINT_API_ESTOQUE_PRODUTO + "/produto/{id}", dadosProduto.id());

    }
    @Entao("o estoque do produto é atualizado com sucesso")
    public void o_estoque_do_produto_é_atualizado_com_sucesso() {
        response.then()
                .statusCode(HttpStatus.ACCEPTED.value());
    }

    @Quando("requisitar a listagem de estoque de todos os produtos")
    public void requisitar_a_listagem_de_estoque_de_todos_os_produtos() {
        response = when()
                .get(ENDPOINT_API_ESTOQUE_PRODUTO + "/total");
    }
    @Entao("a listagem de estoque dos produtos é apresentada com sucesso")
    public void a_listagem_de_estoque_dos_produtos_é_apresentada_com_sucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("schemas/lista_dados_estoque_produto.schema.json"));
    }

    @Quando("requisitar o cadastro de preço do produto")
    public void requisitar_o_cadastro_de_preço_do_produto() {
        DadosCadastroPrecificacaoProduto dadosCadastroPrecificacaoProduto = ProdutoHelper
                .gerarDadosCadastroPrecificacaoProduto();

        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(dadosCadastroPrecificacaoProduto)
                .when()
                .post(ENDPOINT_API_PRECIFICACAO_PRODUTO);
    }
    @Entao("o preço do produto é cadastrado com sucesso")
    public void o_preço_do_produto_é_cadastrado_com_sucesso() {
        response.then()
                .statusCode(HttpStatus.CREATED.value());
    }
    @Entao("a informacao deve ser apresentada")
    public void a_informacao_deve_ser_apresentada() {
       response.then()
               .body(matchesJsonSchemaInClasspath("schemas/dados_precificacao_produto.schema.json"));
    }

    @Quando("requisitar atualizacao de preço de um produto")
    public void requisitar_atualizacao_de_preço_de_um_produto() {
        DadosAtualizacaoPrecificacaoProduto dadosAtualizacaoPrecificacaoProduto = ProdutoHelper
                .gerarDadosAtualizacaoPrecoProduto();

        response = given()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(dadosAtualizacaoPrecificacaoProduto)
                .when()
                    .put(ENDPOINT_API_PRECIFICACAO_PRODUTO + "/produto/{id}", dadosProduto.id());
    }
    @Entao("o preço do produto é atualizado com sucesso")
    public void o_preço_do_produto_é_atualizado_com_sucesso() {
        response.then()
                .statusCode(HttpStatus.ACCEPTED.value());
    }

    @Quando("requisitar a listagem de preços de todos os produtos no catálogo")
    public void requisitar_a_listagem_de_preços_de_todos_os_produtos_no_catálogo() {
        response = when()
                .get(ENDPOINT_API_PRECIFICACAO_PRODUTO + "/produto/todos");
    }
    @Entao("a listagem é apresentada com sucesso")
    public void a_listagem_é_apresentada_com_sucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("schemas/lista_dados_precificacao_produto.schema.json"));
    }
}
