package com.fiap.postech.techchallenge.fastfoodproductmanagement.application.api.produto;

import com.fiap.postech.techchallenge.fastfoodproductmanagement.application.api.produto.records.*;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Produto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto.estoque.AtualizacaoEstoqueProduto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto.estoque.CadastroEstoqueProduto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto.estoque.ListagemEstoqueProduto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto.estoque.SubtracaoEstoqueProduto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto.preco.AtualizacaoPrecoProduto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto.preco.CadastroPrecoProduto;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.usecases.produto.preco.ListagemPrecoProduto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/preco")
@Tag(name = "Preço", description = "Rest API para operações de precificação de produtos do fastfood management system")
public class PrecoProdutoController {

  private final CadastroPrecoProduto cadastroPrecoProduto;
  private final AtualizacaoPrecoProduto atualizacaoPrecoProduto;
  private final ListagemPrecoProduto listagemPrecoProduto;


  public PrecoProdutoController(CadastroPrecoProduto cadastroPrecoProduto, AtualizacaoPrecoProduto atualizacaoPrecoProduto, ListagemPrecoProduto listagemPrecoProduto,
                                ListagemEstoqueProduto listagemEstoqueProduto) {

    this.cadastroPrecoProduto = cadastroPrecoProduto;
    this.atualizacaoPrecoProduto = atualizacaoPrecoProduto;
    this.listagemPrecoProduto = listagemPrecoProduto;
  }

  @Operation(summary = "Cadastrar preço do produto")
  @PostMapping
  public ResponseEntity<DadosPrecificacaoProduto> cadastrarPrecoProduto(
      @Valid @RequestBody DadosCadastroPrecificacaoProduto dadosCadastroPrecificacaoProduto) {

    Produto produto =
            cadastroPrecoProduto.cadastrar(dadosCadastroPrecificacaoProduto);

    DadosPrecificacaoProduto dadosPrecificacaoProduto = new DadosPrecificacaoProduto(produto);
    return ResponseEntity.ok().body(dadosPrecificacaoProduto);
  }


  @Operation(summary = "Atualizar preço do produto")
  @PutMapping("/produto/{id}")
  public ResponseEntity<DadosPrecificacaoProduto> atualizarPrecoProduto(
          @RequestBody DadosAtualizacaoPrecificacaoProduto dadosAtualizacaoPrecificacaoProduto, @PathVariable Integer id) {

    Produto produto = atualizacaoPrecoProduto.atualizar(id, dadosAtualizacaoPrecificacaoProduto);

    return ResponseEntity.ok(new DadosPrecificacaoProduto(produto));
  }


  @Operation(summary = "Listar preço de todos os produtos no catálogo")
  @GetMapping("/produto/todos")
  public ResponseEntity<List<DadosPrecificacaoProduto>> listarPrecoProdutos() {

    List<Produto> produtos = listagemPrecoProduto.listarPrecoProdutos();

    return Objects.nonNull(produtos)
        ? ResponseEntity.ok(produtos.stream().map(DadosPrecificacaoProduto::new).collect(Collectors.toList()))
        : ResponseEntity.notFound().build();
  }

  @Operation(summary = "Listar preço do produto por Id")
  @GetMapping("/produto/{id}")
  public ResponseEntity<DadosPrecificacaoProduto> listarPrecoProdutoPorId(@PathVariable Integer id) {

    Produto produto = listagemPrecoProduto.listarPrecoDoProdutoPorId(id);

    return ResponseEntity.ok(new DadosPrecificacaoProduto(produto));
  }
}
