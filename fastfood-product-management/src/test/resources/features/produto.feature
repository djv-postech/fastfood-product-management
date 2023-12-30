# language: pt

  Funcionalidade: Produto

    Cenario: Cadastrar produto no catalago
      Quando cadastrar um novo produto
      Entao o produto é cadastrado no catalogo com sucesso
      E deve ser apresentado


    Cenario: Pesquisar produto no catalogo
      Dado que um produto já foi cadastrado no catalogo
      Quando quando buscar o produto
      Entao o produto deve ser exibido com sucesso

    Cenario: Alterar produto no catalogo
      Dado que um produto já foi cadastrado no catalogo
      Quando efetuar requisicao para alterar um produto
      Entao o produto é atualizado com sucesso
      E deve ser apresentado

    Cenario:
      Dado que um produto já foi cadastrado no catalogo
      Quando requisitar a remocao do produto
      Entao o produto é removido do catalogo com sucesso