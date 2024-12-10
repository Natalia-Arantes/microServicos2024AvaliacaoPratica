package com.nataliaarantes.api.product.product_api.controllers;

import com.nataliaarantes.api.product.product_api.models.dto.CategoryDTO;
import com.nataliaarantes.api.product.product_api.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
  private final CategoryService categoryService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CategoryDTO save(@RequestBody CategoryDTO categoryDTO) {
    return categoryService.save(categoryDTO);
  }

  @GetMapping
  public List<CategoryDTO> getAll() {
    return categoryService.getAll();
  }

  @GetMapping("/pageable")
  public Page<CategoryDTO> getAllPages(Pageable pageable) {
    return categoryService.getAllPages(pageable);
  }

  @PutMapping("/{id}")
  public CategoryDTO update(@RequestBody CategoryDTO categoryDTO, @PathVariable String id) {
    return categoryService.update(categoryDTO, id);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable String id) {
    categoryService.delete(id);
  }

}
