package com.nataliaarantes.api.product.product_api.repositories;

import com.nataliaarantes.api.product.product_api.models.Shop;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ShopRepository extends MongoRepository<Shop, String> {
  List<Shop> findByUserId(String cpf);

  @Query("{ 'itens.product_identifier': ?0 }")
  List<Shop> findByProductIdentifier(String identifier);
  List<Shop> findByDate(LocalDate date);

  @Query("{ $and: [ " +
      "{ 'date': { $gte: ?0, $lte: ?1 } }, " +
      "{ 'total': { $gte: ?2 } } " +
      "] }")
  List<Shop> findByFilters(LocalDate startDate, LocalDate endDate, Double minValue);

  @Query("{ 'date': { $gte: ?0, $lte: ?1 } }")
  List<Shop> findByDateRange(LocalDate startDate, LocalDate endDate);


}

