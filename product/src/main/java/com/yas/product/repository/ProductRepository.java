package com.yas.product.repository;

import com.yas.product.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository
    extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {}
