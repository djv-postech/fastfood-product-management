package com.fiap.postech.fastfood.product.management.infra.persistence.repository;

import com.fiap.postech.fastfood.product.management.core.produto.Categoria;
import com.fiap.postech.fastfood.product.management.infra.persistence.repository.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProdutoRepositoryMysql extends JpaRepository<ProdutoEntity, Long> {

    List<ProdutoEntity> findByCategoria(Categoria categoria);

    Optional<ProdutoEntity> findByNome(String nome);
}
