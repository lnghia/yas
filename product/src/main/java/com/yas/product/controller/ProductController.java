package com.yas.product.controller;

import com.yas.product.exception.NotFoundException;
import com.yas.product.model.Product;
import com.yas.product.repository.ProductRepository;
import com.yas.product.viewmodel.*;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.yas.product.specification.ProductSpecification.*;

import java.util.List;

@RestController
@RequestMapping(path = "/products")
public class ProductController {
  private final ProductRepository productRepository;

  public ProductController(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @GetMapping
  public ResponseEntity<List<ProductGetVm>> listProducts(
      @RequestParam(defaultValue = "0") Integer page,
      @RequestParam(defaultValue = "10") Integer size) {
    Pageable pageable = PageRequest.of(page, size);

    List<ProductGetVm> productGetDetailVmList =
        productRepository.findAll(pageable).stream().map(ProductGetVm::fromModel).toList();

    return ResponseEntity.ok(productGetDetailVmList);
  }

  @GetMapping("/{id}")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Ok",
            content = @Content(schema = @Schema(implementation = ProductGetDetailVm.class))),
        @ApiResponse(
            responseCode = "404",
            description = "Not found",
            content = @Content(schema = @Schema(implementation = ErrorVm.class)))
      })
  public ResponseEntity<ProductGetDetailVm> getById(@PathVariable("id") Long id) {
    Product product =
        productRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException(String.format("Product %s is not found", id)));

    return ResponseEntity.ok(ProductGetDetailVm.fromModel(product));
  }

  @GetMapping
  public ResponseEntity<PageableProductListVm> filterProducts(
      @RequestBody ProductFilterPostVm requestBody,
      @RequestParam(defaultValue = "0") Integer page,
      @RequestParam(defaultValue = "10") Integer size) {
    Pageable pageable = PageRequest.of(page, size);

    PageableProductListVm result =
        PageableProductListVm.fromModel(
            productRepository.findAll(
                Specification.where(
                    hasSlugLike(requestBody.slug())
                        .and(hasBrandId(requestBody.brandId()))
                        .and(hasCategoryIds(List.of(requestBody.categoryId())))),
                pageable));

    return ResponseEntity.ok(result);
  }
}
