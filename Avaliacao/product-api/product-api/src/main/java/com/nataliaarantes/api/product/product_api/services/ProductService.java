package com.nataliaarantes.api.product.product_api.services;

import com.nataliaarantes.api.product.product_api.models.Category;
import com.nataliaarantes.api.product.product_api.models.Product;
import com.nataliaarantes.api.product.product_api.models.dto.CategoryDTO;
import com.nataliaarantes.api.product.product_api.models.dto.ProductDTO;
import com.nataliaarantes.api.product.product_api.repositories.CategoryRepository;
import com.nataliaarantes.api.product.product_api.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;
  private final CategoryRepository categoryRepository;

  public ProductDTO save(ProductDTO productDTO) {
    Category category = categoryRepository.findById(productDTO.getCategory().getId()).orElseThrow(() -> new RuntimeException());
    String productIdentifier = String.valueOf(UUID.randomUUID());

    productDTO.getCategory().setNome(category.getNome());
    productDTO.setProductIdentifier(productIdentifier);

    Product product = Product.convert(productDTO);
    product = productRepository.save(product);
    return ProductDTO.convert(product);
  }

  public List<ProductDTO> getAll() {
    List<Product> allProducts = productRepository.findAll();

    return allProducts.stream()
        .map(ProductDTO::convert)
        .toList();
  }

  public ProductDTO getById(String id) {
    Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException());
    return ProductDTO.convert(product);
  }

  public Page<ProductDTO> getAllPages(Pageable pageable) {
    Page<Product> pages = productRepository.findAll(pageable);
    return pages.map(ProductDTO::convert);
  }

  public ProductDTO getByIdentifier(String productIdentifier) {
    Product product = productRepository.findByProductIdentifier(productIdentifier);
    if(product != null) {
      return ProductDTO.convert(product);
    }

    throw new RuntimeException();
  }

  public List<ProductDTO> getProductByCategoryId(String categoryId) {
    Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException());
    List<Product> allByCategory = productRepository.findAllByCategory(category);

    return allByCategory.stream()
        .map(ProductDTO::convert)
        .toList();
  }


  public ProductDTO update(ProductDTO productDTO, String id) {
    Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException());

    if(productDTO.getNome() != null && !product.getNome().equals(productDTO.getNome())) {
      product.setNome(productDTO.getNome());
    }

    if(productDTO.getDescricao() != null && !product.getDescricao().equals(productDTO.getDescricao())) {
      product.setDescricao(productDTO.getDescricao());
    }

    if(productDTO.getPreco() != null && !product.getPreco().equals(productDTO.getPreco())) {
      product.setPreco(productDTO.getPreco());
    }

    if(productDTO.getCategory() != null && productDTO.getCategory().getId() != null &&
        !product.getCategory().getId().equals(productDTO.getCategory().getId())
    ) {
      CategoryDTO dtoCategory = productDTO.getCategory();
      Category category = categoryRepository.findById(dtoCategory.getId()).orElseThrow(() -> new RuntimeException());

      product.setCategory(category);
    }

    productRepository.save(product);
    return ProductDTO.convert(product);
  }

  public void delete(String id) {
    Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException());
    productRepository.delete(product);
  }



}
