package com.yas.product.viewmodel;

import com.yas.product.model.Product;

public record ProductGetDetailVm (
        long id,
        String name,
        String shortDescription,
        String description,
        String specification,
        String sku,
        String gtin,
        BrandVm brand) {
    public static ProductGetDetailVm fromModel(Product product) {
        return new ProductGetDetailVm(
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
