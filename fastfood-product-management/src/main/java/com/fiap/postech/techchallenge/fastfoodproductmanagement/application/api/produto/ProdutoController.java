package com.fiap.postech.techchallenge.fastfoodproductmanagement.application.api.produto;

import com.fiap.postech.techchallenge.fastfoodproductmanagement.application.api.produto.records.DadosCadastroProduto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.application.api.produto.records.DadosProduto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Categoria;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Produto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/produto")
@Tag(name = "Produtos", description = "Rest api para operações de produtos")
public class ProdutoController {

  private final CadastroDeProduto cadastrarProduto;
  private final ListagemDeProdutos listagemDeProdutos;
  private final ListagemDeProdutoPorId listagemDeProdutoPorId;
  private final ListagemDeProdutosPorCategoria listagemDeProdutosPorCategoria;
  private final AtualizacaoDeProduto atualizacaoDeProduto;
  private final ExclusaoDeProduto exclusaoDeProduto;

  public ProdutoController(CadastroDeProduto cadastrarProduto, ListagemDeProdutos listagemDeProdutos, ListagemDeProdutoPorId listagemDeProdutoPorId,
      ListagemDeProdutosPorCategoria listagemDeProdutosPorCategoria, AtualizacaoDeProduto atualizacaoDeProduto, ExclusaoDeProduto exclusaoDeProduto) {
    this.cadastrarProduto = cadastrarProduto;
    this.listagemDeProdutos = listagemDeProdutos;
    this.listagemDeProdutoPorId = listagemDeProdutoPorId;
    this.listagemDeProdutosPorCategoria = listagemDeProdutosPorCategoria;
    this.atualizacaoDeProduto = atualizacaoDeProduto;
    this.exclusaoDeProduto = exclusaoDeProduto;
  }

  @Operation(summary = "Cadastrar produto")
  @PostMapping
  public ResponseEntity<DadosProduto> cadastrarProduto(
      @Valid @RequestBody DadosCadastroProduto dadosCadastroProduto) {

    Produto produtoCadastrado =
        cadastrarProduto.cadastrar(
            new Produto(
                dadosCadastroProduto.nome(),
                dadosCadastroProduto.descricao(),
                dadosCadastroProduto.categoria(),
                dadosCadastroProduto.preco(),
                dadosCadastroProduto.quantidade()));

    DadosProduto dadosProduto = new DadosProduto(produtoCadastrado);
    return ResponseEntity.ok().body(dadosProduto);
  }

  @Operation(summary = "Listar produto por Id")
  @GetMapping("/{id}")
  public ResponseEntity<DadosProduto> listarProduto(@PathVariable Integer id) {
    Produto produto = listagemDeProdutoPorId.listarProdutoPorId(id);

    return Objects.nonNull(produto)
        ? ResponseEntity.ok(new DadosProduto(produto))
        : ResponseEntity.notFound().build();
  }

  @Operation(summary = "Atualizar produto")
  @PutMapping("/{id}")
  public ResponseEntity<DadosProduto> atualizarProduto(
      @PathVariable Integer id, @Valid @RequestBody DadosCadastroProduto dadosCadastroProduto) {
    Produto produto =
        atualizacaoDeProduto.atualizarDadosProduto(
            id,
            new Produto(
                dadosCadastroProduto.nome(),
                dadosCadastroProduto.descricao(),
                dadosCadastroProduto.categoria(),
                dadosCadastroProduto.preco(),
                dadosCadastroProduto.quantidade()));
    return ResponseEntity.ok(new DadosProduto(produto));
  }

  @Operation(summary = "Deletar produto do catalogo")
  @DeleteMapping("/{id}")
  public ResponseEntity<DadosProduto> removerProdutoDoCatalogo(@PathVariable Integer id) {
    exclusaoDeProduto.removerProdutoDoCatalogo(id);
    return ResponseEntity.ok().build();
  }

  @Operation(summary = "Listar todos os produtos")
  @GetMapping("/todos")
  public ResponseEntity<List<DadosProduto>> listarProdutos() {
    List<Produto> produtos = listagemDeProdutos.listarTodosOsProdutos();
    return Objects.nonNull(produtos)
        ? ResponseEntity.ok(produtos.stream().map(DadosProduto::new).collect(Collectors.toList()))
        : ResponseEntity.notFound().build();
  }

  @Operation(summary = "Listar produtos por categoria")
  @GetMapping
  public ResponseEntity<List<DadosProduto>> listarProdutosPorCategoria(
      @RequestParam("categoria") final Categoria categoria) {
    List<Produto> produtos = listagemDeProdutosPorCategoria.listaProdutosPorCategoria(categoria);
    return Objects.nonNull(produtos)
        ? ResponseEntity.ok(produtos.stream().map(DadosProduto::new).collect(Collectors.toList()))
        : ResponseEntity.notFound().build();
  }
}
