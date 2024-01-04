package com.fiap.postech.techchallenge.fastfoodproductmanagement.infra.persistence.repository;

import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Categoria;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Produto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto.ProdutoHelper;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
@ActiveProfiles("test")
class ProdutoRepositoryImplTest {

    @Autowired
    private ProdutoRepositoryMysql produtoRepositoryMysql;

    @Autowired
    private ProdutoRepositoryImpl produtoRepositoryImpl;

    @AfterEach
    void cleanUp() {
        this.produtoRepositoryMysql.deleteAll();
    }

    @DisplayName("Teste - Deve permitir cadastrar produto no catálogo com sucesso")
    @Test
    public void devePermitirCadastrarProdutoNoCatalogo(){

        //Arrange
        Produto produto = ProdutoHelper.gerarProduto("Big Mac", "Descricao Big Mac", Categoria.LANCHE);

        //Act
        Produto produtoCadastrado = produtoRepositoryImpl.cadastrarProduto(produto);

        //Assert
        Assertions.assertThat(produtoCadastrado).isInstanceOf(Produto.class);
        Assertions.assertThat(produtoCadastrado.getId()).isNotNull();
        Assertions.assertThat(produtoCadastrado.getNome()).isEqualTo(produto.getNome());
        Assertions.assertThat(produtoCadastrado.getDescricao()).isEqualTo(produto.getDescricao());
        Assertions.assertThat(produtoCadastrado.getCategoria()).isEqualTo(produto.getCategoria());
    }

    @DisplayName("Teste - Deve permitir buscar produto do catálogo por Id")
    @Test
    public void devePermitirBuscarProdutoDoCatalogoPorId(){

        //Arrange
        Produto produto = ProdutoHelper.gerarProduto("Big Mac", "Descricao Big Mac", Categoria.LANCHE);

        Produto produtoSalvo = produtoRepositoryImpl.cadastrarProduto(produto);

        //Act
        Produto produtoRetornado = produtoRepositoryImpl.listarProdutoPorId(produtoSalvo.getId());

        //Assert
        Assertions.assertThat(produtoSalvo.getId()).isEqualTo(produtoRetornado.getId());
        Assertions.assertThat(produtoSalvo.getNome()).isEqualTo(produtoRetornado.getNome());
        Assertions.assertThat(produtoSalvo.getDescricao()).isEqualTo(produtoRetornado.getDescricao());
        Assertions.assertThat(produtoSalvo.getCategoria()).isEqualTo(produtoRetornado.getCategoria());
    }

    @DisplayName("Teste - Deve permitir remover produto do catálogo por ID")
    @Test
    public void devePermitirRemoverProdutoDoCatalogoPorID(){

        //Arrange
        Produto produto = ProdutoHelper.gerarProduto("Big Mac", "Descricao Big Mac", Categoria.LANCHE);

        Produto produtoSalvo = produtoRepositoryImpl.cadastrarProduto(produto);

        //Act
        produtoRepositoryImpl.removerProduto(produtoSalvo.getId());

        //Assert
        Produto produtoBuscado = produtoRepositoryImpl.listarProdutoPorId(
                produtoSalvo.getId());

        Assertions.assertThat(produtoBuscado).isNull();

    }

    @DisplayName("Teste - Deve permitir retornar todos os produtos do catálogo")
    @Test
    public void devePermitirListarTodosOsProdutosDoCatalogo(){

        //Arrange
        Produto produto1 = ProdutoHelper.gerarProduto("Big Mac", "Descricao Big Mac", Categoria.LANCHE);
        Produto produto2 = ProdutoHelper.gerarProduto("Big Tasty", "Descricao Big Tasty", Categoria.LANCHE);

        produtoRepositoryImpl.cadastrarProduto(produto1);
        produtoRepositoryImpl.cadastrarProduto(produto2);

        //Act
        List<Produto> listaProdutos = produtoRepositoryImpl.listarProdutos();


        //Assert
        Assertions.assertThat(listaProdutos).isNotNull();
        Assertions.assertThat(listaProdutos.size()).isEqualTo(2);
    }

    @DisplayName("Teste - Deve permitir buscar produtos por categoria")
    @Test
    public void devePermitirBuscarProdutosPorCategoria(){

        //Arrange
        Produto produto1 = ProdutoHelper.gerarProduto("Big Mac", "Descricao Big Mac", Categoria.LANCHE);
        Produto produto2 = ProdutoHelper.gerarProduto("Big Tasty", "Descricao Big Tasty", Categoria.LANCHE);
        Produto produto3 = ProdutoHelper.gerarProduto("Mc Flury", "Descricao Mc Flury", Categoria.SOBREMESA);

        produtoRepositoryImpl.cadastrarProduto(produto1);
        produtoRepositoryImpl.cadastrarProduto(produto2);
        produtoRepositoryImpl.cadastrarProduto(produto3);

        //Act
        List<Produto> listaDeProdutos = produtoRepositoryImpl
                .listaProdutosPorCategoria(Categoria.LANCHE);

        //Assert
        Assertions.assertThat(listaDeProdutos).isNotNull();
        Assertions.assertThat(listaDeProdutos.size()).isEqualTo(2);

    }

    @DisplayName("Teste - Deve permitir busca produto pelo nome")
    @Test
    public void devePermitirBuscarProdutoDoCatalogoPorNome(){

        //Arrange
        Produto produto = ProdutoHelper.gerarProduto("Big Mac", "Descricao Big Mac", Categoria.LANCHE);

        //Act
        Produto produtoCadastrado = produtoRepositoryImpl.cadastrarProduto(produto);

        //Act
        Produto produtoBuscado = produtoRepositoryImpl.listarProdutoPorNome(produtoCadastrado.getNome());

        //Assert
        Assertions.assertThat(produtoBuscado).isNotNull();
        Assertions.assertThat(produtoBuscado.getNome()).isEqualTo(produtoCadastrado.getNome());
    }

    @DisplayName("Teste - Deve permitir cadastrar estoque em produto do catálogo")
    @Test
    public void devePermitirCadastrarEstoqueNoProdutoDoCatalogo(){

        //Arrange
        Produto produto = ProdutoHelper.gerarProduto("Big Mac", "Descricao Big Mac", Categoria.LANCHE);
        //Act
        produtoRepositoryImpl.cadastrarProduto(produto);

        produto.adicionarEstoque(100);

        //Act
        Produto produtoEstoque = produtoRepositoryImpl.cadastrarEstoqueProduto(produto);

        //Assert
        Assertions.assertThat(produtoEstoque).isNotNull();
        Assertions.assertThat(produtoEstoque.getQuantidadeEstoque()).isEqualTo(produto.getQuantidadeEstoque());
    }

    @DisplayName("Teste - Deve permitir cadastrar preço em produto do catálogo")
    @Test
    public void devePermitirCadastrarPrecoNoProdutoDoCatalogo(){

        //Arrange
        Produto produto = ProdutoHelper.gerarProduto("Big Mac", "Descricao Big Mac", Categoria.LANCHE);
        //Act
        produtoRepositoryImpl.cadastrarProduto(produto);

        produto.precificarProduto(new BigDecimal(10));

        //Act
        Produto produtoComPreco = produtoRepositoryImpl.cadastrarPrecoProduto(produto);

        //Assert
        Assertions.assertThat(produtoComPreco).isNotNull();
        Assertions.assertThat(produtoComPreco.getQuantidadeEstoque()).isEqualTo(produto.getQuantidadeEstoque());
    }


}