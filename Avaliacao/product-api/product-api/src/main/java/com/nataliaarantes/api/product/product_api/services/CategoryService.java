package com.nataliaarantes.api.product.product_api.services;

import com.nataliaarantes.api.product.product_api.models.Category;
import com.nataliaarantes.api.product.product_api.models.dto.CategoryDTO;
import com.nataliaarantes.api.product.product_api.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

  private final CategoryRepository repository;

  public CategoryDTO save(CategoryDTO categoryDTO) {
    Category category = repository.save(Category.convert(categoryDTO));
    return CategoryDTO.convert(category);
  }

  public List<CategoryDTO> getAll() {
    List<Category> allCategories = repository.findAll();

    return allCategories.stream()
        .map(CategoryDTO::convert)
        .toList();
  }

  public Page<CategoryDTO> getAllPages(Pageable pageable) {
    Page<Category> pages = repository.findAll(pageable);
    return pages.map(CategoryDTO::convert);
  }

  public CategoryDTO update(CategoryDTO categoryDTO, String id) {
    Category category = repository.findById(id).orElseThrow(() -> new RuntimeException());

    category.setNome(categoryDTO.getNome());
    repository.save(category);

    return CategoryDTO.convert(category);
  }

  public void delete(String id) {
    Category category = repository.findById(id).orElseThrow(() -> new RuntimeException());
    repository.delete(category);
  }

}
