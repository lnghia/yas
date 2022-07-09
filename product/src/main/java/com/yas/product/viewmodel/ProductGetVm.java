package com.yas.product.viewmodel;

import com.yas.product.model.Product;

public record ProductGetVm (
        long id,
        String name,
        String shortDescription,
        Long thumbnailMediaId,
        String metaDescription,
        BrandVm brand) {

    public static ProductGetVm fromModel(Product product) {
        return new ProductGetVm(
                product.getId(),
                product.getName(),
                product.getShortDescription(),
                product.getThumbnailMediaId(),
                product.getMetaDescription(),
                BrandVm.fromModel(product.getBrand())
        );
    }
}
