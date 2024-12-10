package com.nataliaarantes.api.product.product_api.models.dto;

import com.nataliaarantes.api.product.product_api.models.Category;
import com.nataliaarantes.api.product.product_api.models.Product;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
  private String id;
  private String productIdentifier;

  @NotBlank(message = "Nome é obrigatório")
  private String nome;

  @NotBlank(message = "Descrição é obrigatória")
  private String descricao;

  @NotBlank(message = "Preço é obrigatório")
  private Double preco;

  @NotBlank(message = "Categoria é obrigatório")
  private CategoryDTO category;


  public static ProductDTO convert(Product product) {
    return new ProductDTO(
        product.getId(),
        product.getProductIdentifier(),
        product.getNome(),
        product.getDescricao(),
        product.getPreco(),
        CategoryDTO.convert(product.getCategory())
    );
  }
}
