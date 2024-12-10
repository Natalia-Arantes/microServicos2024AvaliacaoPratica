package com.nataliaarantes.api.product.product_api.models;

import com.nataliaarantes.api.product.product_api.models.dto.ShopDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "shop")
public class Shop {

  @Id
  private String id;

  @Field("user_identifier")
  private String userId;

  @Field("date")
  private LocalDate date;

  @Field("itens")
  private List<Item> itens;

  @Field("total")
  private Double total;


  private static Shop convert(ShopDTO dto) {
    return null;
  }
}
