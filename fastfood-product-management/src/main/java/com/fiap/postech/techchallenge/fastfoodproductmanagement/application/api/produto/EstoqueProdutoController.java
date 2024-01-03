package com.fiap.postech.techchallenge.fastfoodproductmanagement.application.api.produto;

import com.fiap.postech.techchallenge.fastfoodproductmanagement.application.api.produto.records.DadosCadastroEstoqueProduto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.application.api.produto.records.DadosEstoqueProduto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Produto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto.*;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto.estoque.AtualizacaoEstoqueProduto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto.estoque.CadastroEstoqueProduto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto.estoque.ListagemEstoqueProduto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto.estoque.SubtracaoEstoqueProduto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/estoque")
@Tag(name = "Estoque", description = "Rest API para operações de estoque de produtos do fastfood management system")
public class EstoqueProdutoController {

  private final CadastroEstoqueProduto cadastroEstoqueProduto;
  private final SubtracaoEstoqueProduto subtracaoEstoqueProduto;
  private final AtualizacaoEstoqueProduto atualizacaoEstoqueProduto;
  private final ListagemEstoqueProduto listagemEstoqueProduto;


  public EstoqueProdutoController(CadastroEstoqueProduto cadastroEstoqueProduto, SubtracaoEstoqueProduto subtracaoEstoqueProduto, AtualizacaoEstoqueProduto atualizacaoEstoqueProduto,
                                  ListagemEstoqueProduto listagemEstoqueProduto) {

    this.cadastroEstoqueProduto = cadastroEstoqueProduto;
    this.subtracaoEstoqueProduto = subtracaoEstoqueProduto;
    this.atualizacaoEstoqueProduto = atualizacaoEstoqueProduto;
    this.listagemEstoqueProduto = listagemEstoqueProduto;
  }

  @Operation(summary = "Cadastrar estoque produto")
  @PostMapping
  public ResponseEntity<DadosEstoqueProduto> cadastrarEstoqueProduto(
          @Valid @RequestBody DadosCadastroEstoqueProduto dadosCadastroEstoqueProduto, UriComponentsBuilder builder) {

    Produto produto =
        cadastroEstoqueProduto.cadastrar(dadosCadastroEstoqueProduto.idProduto(), dadosCadastroEstoqueProduto.quantidadeEstoque());

    URI uri = builder.path("/estoque/{id}").buildAndExpand(produto.getId()).toUri();
    return ResponseEntity.created(uri).body(new DadosEstoqueProduto(produto));
  }

  @Operation(summary = "Subtrai estoque do produto de Id")
  @PostMapping("/produto/{id}")
  public ResponseEntity<DadosEstoqueProduto> subtrairEstoqueProduto(@PathVariable Integer id,
          @RequestParam("quantidade") Integer quantidade) {
    Produto produto = subtracaoEstoqueProduto.subtrair(id, quantidade);

    return Objects.nonNull(produto)
        ? ResponseEntity.ok(new DadosEstoqueProduto(produto))
        : ResponseEntity.notFound().build();
  }

  @Operation(summary = "Atualizar estoque de produto")
  @PutMapping("/produto/{id}")
  public ResponseEntity<DadosEstoqueProduto> atualizarEstoqueProduto(@PathVariable Integer id,
                                                              @RequestParam("quantidade") Integer quantidade) {
    Produto produto =
            atualizacaoEstoqueProduto.atualizar(id, quantidade);

    return ResponseEntity.accepted().body(new DadosEstoqueProduto(produto));
  }


  @Operation(summary = "Listar estoque de todos os produtos")
  @GetMapping("/total")
  public ResponseEntity<List<DadosEstoqueProduto>> listarEstoqueProdutos() {
    List<Produto> produtos = listagemEstoqueProduto.total();

    return Objects.nonNull(produtos)
        ? ResponseEntity.ok(produtos.stream().map(DadosEstoqueProduto::new).collect(Collectors.toList()))
        : ResponseEntity.notFound().build();
  }

  @Operation(summary = "Listar estoque produto por Id")
  @GetMapping("/produto/{id}")
  public ResponseEntity<DadosEstoqueProduto> listarEstoqueProdutoPorId(
      @PathVariable final Integer id) {
    Produto produto = listagemEstoqueProduto.listarEstoqueProdutoPorId(id);

    DadosEstoqueProduto dadosEstoqueProduto = new DadosEstoqueProduto(produto);
    return ResponseEntity.ok().body(dadosEstoqueProduto);
  }
}
