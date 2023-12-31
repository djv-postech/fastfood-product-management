package com.fiap.postech.techchallenge.fastfoodproductmanagement.application.api.produto;

import com.fiap.postech.techchallenge.fastfoodproductmanagement.application.api.produto.records.DadosCadastroProduto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.application.api.produto.records.DadosProduto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Categoria;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Produto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.exception.ProdutoNotFoundException;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/produto")
@Tag(name = "Produtos", description = "Rest API para operações de produtos do fastfood management system")
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

  @Operation(summary = "Cadastrar produto no catálogo")
  @PostMapping
  public ResponseEntity<DadosProduto> cadastrarProduto(
          @Valid @RequestBody DadosCadastroProduto dadosCadastroProduto, UriComponentsBuilder builder) {

    Produto produtoCadastrado =
        cadastrarProduto.cadastrar(
            new Produto(
                dadosCadastroProduto.nome(),
                dadosCadastroProduto.descricao(),
                dadosCadastroProduto.categoria()));

    URI uri = builder.path("/produto/{id}").buildAndExpand(produtoCadastrado.getId()).toUri();
    return ResponseEntity.created(uri).body(new DadosProduto(produtoCadastrado));
  }

  @Operation(summary = "Listar produto por Id")
  @GetMapping("/{id}")
  public ResponseEntity<?> listarProduto(@PathVariable Integer id) {
    try {

      Produto produto = listagemDeProdutoPorId.listarProdutoPorId(id);
      return ResponseEntity.ok(new DadosProduto(produto));

    } catch (ProdutoNotFoundException exception){
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
              .body(exception.getMessage());
    }
  }

  @Operation(summary = "Atualizar produto do catálogo")
  @PutMapping("/{id}")
  public ResponseEntity<DadosProduto> atualizarProduto(
      @PathVariable Integer id, @Valid @RequestBody DadosCadastroProduto dadosCadastroProduto) {
    Produto produto =
        atualizacaoDeProduto.atualizarDadosProduto(
            id,
            new Produto(
                dadosCadastroProduto.nome(),
                dadosCadastroProduto.descricao(),
                dadosCadastroProduto.categoria()));
    return ResponseEntity.accepted().body(new DadosProduto(produto));
  }

  @Operation(summary = "Deletar produto do catálogo")
  @DeleteMapping("/{id}")
  public ResponseEntity<DadosProduto> removerProdutoDoCatalogo(@PathVariable Integer id) {
    exclusaoDeProduto.removerProdutoDoCatalogo(id);
    return ResponseEntity.ok().build();
  }

  @Operation(summary = "Listar todos os produtos do catálogo")
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
