package br.com.davsantos.productapi.modules.category.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.davsantos.productapi.config.SuccessResponse;
import br.com.davsantos.productapi.modules.category.dtos.CategoryRequest;
import br.com.davsantos.productapi.modules.category.dtos.CategoryResponse;
import br.com.davsantos.productapi.modules.category.services.CategoryService;

@RestController
@RequestMapping(value = "/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService service;

	@PostMapping
	public CategoryResponse save(@RequestBody CategoryRequest request) {
		return service.save(request);
	}

	@GetMapping
	public List<CategoryResponse> findAll() {
		return service.findAll();
	}

	@GetMapping("{id}")
	public CategoryResponse findById(@PathVariable Long id) {
		return service.findByIdResponse(id);
	}

	@PutMapping("{id}")
	public CategoryResponse update(@RequestBody CategoryRequest request, @PathVariable Long id) {
		return service.update(request, id);
	}

	@GetMapping("/description/{description}")
	public List<CategoryResponse> findByDescription(@PathVariable String description) {
		return service.findByDescription(description);
	}

	@DeleteMapping("{id}")
	public SuccessResponse delete(@PathVariable Long id) {
		return service.delete(id);
	}
}
