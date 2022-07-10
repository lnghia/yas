package com.yas.product.viewmodel;

import com.yas.product.model.Product;
import com.yas.product.model.ProductCategory;

import java.util.List;

public record ProductGetDetailVm (
        long id,
        String name,
        String shortDescription,
        Long thumbnailMediaId,
        String metaDescription,
        String description,
        String specification,
        String sku,
        String gtin,
        BrandVm brand,
        List<CategoryGetVm> categories) {
    public static ProductGetDetailVm fromModel(Product product) {
        return new ProductGetDetailVm(
                product.getId(),
                product.getName(),
                product.getShortDescription(),
                product.getThumbnailMediaId(),
                product.getMetaDescription(),
                product.getDescription(),
                product.getSpecification(),
                product.getSku(),
                product.getGtin(),
                BrandVm.fromModel(product.getBrand()),
                product.getProductCategories().stream().map(ProductCategory::getCategory).map(CategoryGetVm::fromModel).toList()
        );
    }
}
