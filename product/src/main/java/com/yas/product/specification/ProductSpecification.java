package com.yas.product.specification;

import com.yas.product.model.Product;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public final class ProductSpecification {
  public static Specification<Product> hasSlugLike(String keyword) {
    return (root, query, criteriaBuilder) ->
        criteriaBuilder.or(
            criteriaBuilder.like(root.get("slug"), "%" + keyword + "%"),
            criteriaBuilder.equal(null, keyword),
            criteriaBuilder.equal(, keyword));
  }

  public static Specification<Product> hasCategoryIds(List<Long> categoryIds) {
    return (root, query, criteriaBuilder) ->
        criteriaBuilder.or(
            root.get("categories").in(categoryIds),
            categoryIds.isEmpty(),
            categoryIds.equals(null));
  }

  public static Specification<Product> hasBrandId(Long brandId) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("brand"), brandId);
  }
}
