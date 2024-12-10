package com.nataliaarantes.api.product.product_api.models.dto;

import com.nataliaarantes.api.product.product_api.models.Shop;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShopDTO {
  private String id;

  @NotBlank
  private String userIdentifier;
  private LocalDate date;

  @NotBlank
  private List<ItemDTO> itens;
  private Double total;

  public static ShopDTO convert(Shop shop) {
    List<ItemDTO> itensDto =  shop.getItens().stream().map(ItemDTO::convert).toList();
    return new ShopDTO(
        shop.getId(),
        shop.getUserId(),
        shop.getDate(),
        itensDto,
        shop.getTotal()
    );
  }
}
