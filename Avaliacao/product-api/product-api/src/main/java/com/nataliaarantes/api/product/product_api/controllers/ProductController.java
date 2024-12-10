package com.nataliaarantes.api.product.product_api.controllers;

import com.nataliaarantes.api.product.product_api.models.dto.ProductDTO;
import com.nataliaarantes.api.product.product_api.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
  private final ProductService productService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ProductDTO save(@RequestBody ProductDTO productDTO) {
    return productService.save(productDTO);
  }

  @GetMapping
  public List<ProductDTO> getAll() {
    return productService.getAll();
  }

  @GetMapping("/pageable")
  public Page<ProductDTO> getAllPages(Pageable pageable) {
    return productService.getAllPages(pageable);
  }

  @GetMapping("/{id}")
  public ProductDTO getById(@PathVariable String id) {
    return productService.getById(id);
  }

  @GetMapping("/{productIdentifier}/identifier")
  public ProductDTO findByIdentifier(@PathVariable String productIdentifier) {
    return productService.getByIdentifier(productIdentifier);
  }

  @GetMapping("/category/{categoryId}")
  public List<ProductDTO> getByIdentifier(@PathVariable String categoryId) {
    return productService.getProductByCategoryId(categoryId);
  }

  @PutMapping("/{id}")
  public ProductDTO update(@RequestBody ProductDTO productDTO, @PathVariable String id) {
    return productService.update(productDTO, id);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable String id) {
    productService.delete(id);
  }

}
