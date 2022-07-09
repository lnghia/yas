package com.yas.product.viewmodel;

import com.yas.product.model.Product;

import java.util.List;

public record ProductGetVm (
        long id,
        String name,
        String shortDescription,
        BrandVm brand) {

    public static ProductGetVm fromModel(Product product) {
        return new ProductGetVm(
                product.getId(),
                product.getName(),
                product.getShortDescription(),
                BrandVm.fromModel(product.getBrand())
        );
    }
}
