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

   Cenario: Pesquisar produto que não existe no catálogo
     Quando pesquisar um produto que nao existe no catalogo
     Entao uma mensagem de erro deve ser apresentada

   Cenario: Pesquisar produto por categoria no catálago
     Dado que um produto já foi cadastrado no catalogo
     Quando buscar um produto por categoria
     Entao a listagem de produtos da categoria é exibida com sucesso

    Cenario: Alterar produto no catalogo
      Dado que um produto já foi cadastrado no catalogo
      Quando efetuar requisicao para alterar um produto
      Entao o produto é atualizado com sucesso
      E deve ser apresentado

    Cenario: Remover produto do catálogo
      Dado que um produto já foi cadastrado no catalogo
      Quando requisitar a remocao do produto
      Entao o produto é removido do catalogo com sucesso


    Cenario: Listar todos os produtos do catálogo
      Dado que um produto já foi cadastrado no catalogo
      Quando evefuar a requiscao da listagem de todos os produtos
      Entao a lista de produtos cadastrados é retornada com sucesso
      E deve ser apresentada