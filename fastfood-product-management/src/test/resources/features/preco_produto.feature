# language: pt

  Funcionalidade: Precificação

    Cenario: Cadastrar preço de um produto
      Dado que um produto já foi cadastrado no catalogo
      Quando requisitar o cadastro de preço do produto
      Entao o preço do produto é cadastrado com sucesso
      E a informacao deve ser apresentada


    Cenario: Atualizar preço de um produto
      Dado que um produto já foi cadastrado no catalogo
      Quando requisitar atualizacao de preço de um produto
      Entao o preço do produto é atualizado com sucesso
      E a informacao deve ser apresentada


    Cenario: Listar preço de todos os produtos
      Dado que um produto já foi cadastrado no catalogo
      Quando requisitar a listagem de preços de todos os produtos no catálogo
      Entao a listagem é apresentada com sucesso