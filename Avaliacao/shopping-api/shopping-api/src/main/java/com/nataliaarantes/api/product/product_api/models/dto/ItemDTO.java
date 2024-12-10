package com.nataliaarantes.api.product.product_api.models.dto;
import com.nataliaarantes.api.product.product_api.models.Item;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {
  @NotBlank
  private String productIdentifier;
  private Double price;

  public static ItemDTO convert(Item item) {
    return new ItemDTO(item.getProductIdentifier(), item.getPrice());
  }
}
