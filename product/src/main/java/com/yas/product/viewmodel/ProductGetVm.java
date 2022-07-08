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
        long gtin,
        double price,
        boolean isAllowedToOrder,
        boolean isPublished,
        boolean isFeatured,
        BrandVm brand,
        List<CategoryGetVm> categories) {

    public static ProductGetVm fromModel(Product product) {
        return new ProductGetVm(
                product.getId(),
                product.getName(),
                product.getShortDescription(),
                product.getDescription(),
                product.getSpecification(),
                product.getSku(),
                product.getGtin(),
                product.getPrice(),
                product.isAllowedToOrder(),
                product.isPublished(),
                product.isFeatured(),
                BrandVm.fromModel(product.getBrand()),
                product.getCategories().stream().map(CategoryGetVm::fromModel).toList()
        );
    }
}
