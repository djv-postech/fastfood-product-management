# language: pt

  Funcionalidade: Estoque

    Cenario: Cadastrar estoque de um produto
      Dado que um produto já foi cadastrado no catalogo
      Quando cadastrar estoque do produto
      Entao o estoque do produto é cadastrado com sucesso
      E a informacao de estoque deve ser apresentada

    Cenario: Subtrair estoque do produto
      Dado que o estoque já foi cadastrado no produto do catalogo
      Quando subrair um valor do estoque do produto
      Entao o valor é subtraido do estoque com sucesso
      E a informacao de estoque deve ser apresentada


    Cenario: Atualizar estoque de um produto
      Dado que o estoque já foi cadastrado no produto do catalogo
      Quando requisitar atualizacao de estoque de um produto
      Entao o estoque do produto é atualizado com sucesso
      E a informacao de estoque deve ser apresentada