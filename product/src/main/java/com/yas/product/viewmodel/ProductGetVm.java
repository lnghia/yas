package com.yas.product.viewmodel;

import com.yas.product.model.Product;

import java.util.List;

public record ProductGetVm (
        long id,
        String name,
        String shortDescription,
        String description,
        String specification,
        String sku,
        String gtin,
        BrandVm brand) {

    public static ProductGetVm fromModel(Product product) {
        return new ProductGetVm(
                product.getId(),
                product.getName(),
                product.getShortDescription(),
                product.getDescription(),
                product.getSpecification(),
                product.getSku(),
                product.getGtin(),
                BrandVm.fromModel(product.getBrand())
        );
    }
}
