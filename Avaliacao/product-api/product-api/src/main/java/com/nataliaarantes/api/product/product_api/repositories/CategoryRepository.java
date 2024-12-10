package com.nataliaarantes.api.product.product_api.repositories;

import com.nataliaarantes.api.product.product_api.models.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {

}

