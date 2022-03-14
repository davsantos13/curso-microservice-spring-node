package br.com.davsantos.productapi.modules.category.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.davsantos.productapi.config.SuccessResponse;
import br.com.davsantos.productapi.config.exceptions.ValidationException;
import br.com.davsantos.productapi.modules.category.dtos.CategoryRequest;
import br.com.davsantos.productapi.modules.category.dtos.CategoryResponse;
import br.com.davsantos.productapi.modules.category.models.Category;
import br.com.davsantos.productapi.modules.category.repositories.CategoryRepository;
import br.com.davsantos.productapi.modules.product.services.ProductService;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;

	@Autowired
	private ProductService productService;

	public CategoryResponse save(CategoryRequest request) {
		checkInformedData(request.getDescription(), "description");

		var category = repository.save(Category.of(request));
		return CategoryResponse.of(category);
	}

	public CategoryResponse update(CategoryRequest request, Long id) {
		checkInformedData(request.getDescription(), "description");
		checkInformedData(id, "id");
		
		
		var category = Category.of(request);
		category.setId(id);

		category = repository.save(category);
		return CategoryResponse.of(category);
	}

	public Category findById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new ValidationException("There's no category for the given ID"));
	}

	public List<CategoryResponse> findByDescription(String description) {
		checkInformedData(description, "description");

		return repository.findByDescriptionIgnoreCaseContaining(description).stream().map(c -> CategoryResponse.of(c))
				.collect(Collectors.toList());
	}

	public CategoryResponse findByIdResponse(Long id) {
		checkInformedData(id, "id");

		return CategoryResponse.of(findById(id));
	}

	public List<CategoryResponse> findAll() {
		return repository.findAll().stream().map(c -> CategoryResponse.of(c)).collect(Collectors.toList());
	}

	public SuccessResponse delete(Long id) {
		checkInformedData(id, "id");

		if (productService.existsByCategoryId(id)) {
			throw new ValidationException("You cannot delete this category because it's already defined by a product");
		}

		repository.deleteById(id);

		return SuccessResponse.create("The category was deleted!");
	}

	private void checkInformedData(Object data, String field) {
		if (org.springframework.util.ObjectUtils.isEmpty(data)) {
			throw new ValidationException("The category " + field + " must be informed");
		}
	}

}
