package com.nataliaarantes.api.product.product_api.models;

import com.nataliaarantes.api.product.product_api.models.dto.CategoryDTO;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "category")
public class Category {
  @Id
  private String id;
  @Field("nome")
  private String nome;

  public static Category convert(CategoryDTO dto) {
    Category category = new Category();
    if(dto.getId() != null) {
      category.setId(dto.getId());
    }
    category.setNome(dto.getNome());

    return category;
  }
}
