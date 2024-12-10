package com.nataliaarantes.api.product.product_api.models;

import com.nataliaarantes.api.product.product_api.models.dto.CategoryDTO;
import com.nataliaarantes.api.product.product_api.models.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "product")
public class Product {
  @Id
  private String id;

  @Field("product_identifier")
  private String productIdentifier;

  @Field("nome")
  private String nome;

  @Field("descricao")
  private String descricao;

  @Field("preco")
  private Double preco;

  @Field("categoria_id")
  @DBRef
  private Category category;

  public static Product convert(ProductDTO dto) {
    Product product = new Product();
    product.setNome(dto.getNome());
    product.setDescricao(dto.getDescricao());
    product.setPreco(dto.getPreco());
    product.setProductIdentifier(dto.getProductIdentifier());
    product.setCategory(Category.convert(dto.getCategory()));

    return product;
  }
}
