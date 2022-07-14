package com.yas.product.repository;

import com.yas.product.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository
    extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
  @Override
  @EntityGraph(attributePaths = {"brand"})
  Page<Product> findAll(Pageable pageable);

  @EntityGraph(attributePaths = {"brand", "productCategories", "productCategories.category"})
  Optional<Product> findById(Long id);
}
