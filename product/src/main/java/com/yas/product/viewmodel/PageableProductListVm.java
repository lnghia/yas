package com.yas.product.viewmodel;

import com.yas.product.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public record PageableProductListVm(
        Long totalElements,
        int totalPages,
        int size,
        List<ProductGetVm> content) {
    public static PageableProductListVm fromModel(Page<Product> pageableProducts) {
        return new PageableProductListVm(
                pageableProducts.getTotalElements(),
                pageableProducts.getTotalPages(),
                pageableProducts.getSize(),
                pageableProducts.getContent().stream().map(ProductGetVm::fromModel).toList()
        );
    }
}
