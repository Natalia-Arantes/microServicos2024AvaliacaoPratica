package com.nataliaarantes.api.product.product_api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "shop")
public class Item {

  @Field("product_identifier")
  private String productIdentifier;

  @Field("price")
  private Double price;
}
