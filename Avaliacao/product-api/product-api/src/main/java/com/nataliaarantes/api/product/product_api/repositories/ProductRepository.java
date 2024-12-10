package com.nataliaarantes.api.product.product_api.repositories;

import com.nataliaarantes.api.product.product_api.models.Category;
import com.nataliaarantes.api.product.product_api.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
  Product findByProductIdentifier(String productIdentifier);
  List<Product> findAllByCategory(Category category);
}
