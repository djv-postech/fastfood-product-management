package com.fiap.postech.techchallenge.fastfoodproductmanagement.infra.persistence.repository;

import com.fiap.postech.techchallenge.fastfoodproductmanagement.core.domain.entities.produto.Categoria;
import com.fiap.postech.techchallenge.fastfoodproductmanagement.infra.persistence.repository.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProdutoRepositoryMysql extends JpaRepository<ProdutoEntity, Integer> {

    List<ProdutoEntity> findByCategoria(Categoria categoria);

    Optional<ProdutoEntity> findByNome(String nome);
}
