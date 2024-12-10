package com.nataliaarantes.api.product.product_api.models.dto;

import com.nataliaarantes.api.product.product_api.models.Category;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
  private String id;
  @NotBlank(message = "Nome da categoria é obrigatório")
  private String nome;


  public static CategoryDTO convert(Category category) {
    return new CategoryDTO(category.getId(), category.getNome());
  }
}
