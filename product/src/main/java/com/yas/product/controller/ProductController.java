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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            content = @Content(schema = @Schema(implementation = ProductGetVm.class))),
        @ApiResponse(
            responseCode = "404",
            description = "Not found",
            content = @Content(schema = @Schema(implementation = ErrorVm.class)))
      })
  public ResponseEntity<ProductGetVm> getById(@PathVariable("id") Long id) {
    Product product =
        productRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException(String.format("Product %s is not found", id)));

    return ResponseEntity.ok(ProductGetVm.fromModel(product));
  }
}