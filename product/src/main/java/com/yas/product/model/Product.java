package com.yas.product.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product")
@Getter
@Setter
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String shortDescription;

  private String description;

  private String specification;

  @NotEmpty
  @Pattern(regexp = "^[a-zA-Z0-9_]*$")
  private String sku;

  private long gtin;

  private double price = 0.0;

  private boolean isAllowedToOrder;

  private boolean isPublished;

  private boolean isFeatured;

  @ManyToOne
  @JoinColumn(name = "brand_id", referencedColumnName = "id")
  private Brand brand;

  @ManyToMany
  @JoinTable(
      name = "product_category",
      joinColumns = {@JoinColumn(name = "product_id", nullable = false, updatable = false)},
      inverseJoinColumns = {@JoinColumn(name = "category_id", nullable = false, updatable = false)})
  private Set<Category> categories = new HashSet<>();
}
