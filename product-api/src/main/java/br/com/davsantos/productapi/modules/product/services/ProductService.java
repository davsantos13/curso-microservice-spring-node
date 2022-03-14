package br.com.davsantos.productapi.modules.product.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.davsantos.productapi.config.SuccessResponse;
import br.com.davsantos.productapi.config.exceptions.ValidationException;
import br.com.davsantos.productapi.modules.category.repositories.CategoryRepository;
import br.com.davsantos.productapi.modules.product.dtos.ProductRequest;
import br.com.davsantos.productapi.modules.product.dtos.ProductResponse;
import br.com.davsantos.productapi.modules.product.models.Product;
import br.com.davsantos.productapi.modules.product.repositories.ProductRepository;
import br.com.davsantos.productapi.modules.supplier.repositories.SupplierRepository;

@Service
public class ProductService {

	private static final Integer ZERO = 0;

	@Autowired
	private ProductRepository repository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private SupplierRepository supplierRepository;

	public ProductResponse save(ProductRequest request) {
		checkProduct(request);

		var category = categoryRepository.findById(request.getCategoryId()).get();
		var supplier = supplierRepository.findById(request.getSupplierId()).get();

		var product = repository.save(Product.of(request, supplier, category));

		return ProductResponse.of(product);
	}

	public ProductResponse update(ProductRequest request, Long id) {
		checkProduct(request);
		checkInformedData(id, "id");

		var category = categoryRepository.findById(request.getCategoryId()).get();
		var supplier = supplierRepository.findById(request.getSupplierId()).get();
		var product = Product.of(request, supplier, category);

		product.setId(id);

		product = repository.save(product);

		return ProductResponse.of(product);
	}

	public List<ProductResponse> findAll() {
		return repository.findAll().stream().map(p -> ProductResponse.of(p)).collect(Collectors.toList());
	}

	public Product findById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new ValidationException("There's no product for the given ID"));
	}

	public ProductResponse findByIdResponse(Long id) {
		return ProductResponse.of(findById(id));
	}

	public List<ProductResponse> findByName(String name) {
		if (org.springframework.util.ObjectUtils.isEmpty(name)) {
			throw new ValidationException("The product name must be informed");
		}

		return repository.findByNameIgnoreCaseContaining(name).stream().map(c -> ProductResponse.of(c))
				.collect(Collectors.toList());
	}

	public List<ProductResponse> findBySupplierId(Long id) {
		if (org.springframework.util.ObjectUtils.isEmpty(id)) {
			throw new ValidationException("The supplier id must be informed");
		}

		return repository.findBySupplierId(id).stream().map(c -> ProductResponse.of(c)).collect(Collectors.toList());
	}

	public List<ProductResponse> findByCategoryId(Long id) {
		if (org.springframework.util.ObjectUtils.isEmpty(id)) {
			throw new ValidationException("The category id must be informed");
		}

		return repository.findByCategoryId(id).stream().map(c -> ProductResponse.of(c)).collect(Collectors.toList());
	}

	public Boolean existsBySupplierId(Long id) {
		return repository.existsBySupplierId(id);
	}

	public Boolean existsByCategoryId(Long id) {
		return repository.existsByCategoryId(id);
	}

	public SuccessResponse delete(Long id) {
		checkInformedData(id, "id");

		repository.deleteById(id);

		return SuccessResponse.create("The product was deleted!");
	}

	private void checkInformedData(Object data, String field) {
		if (org.springframework.util.ObjectUtils.isEmpty(data)) {
			throw new ValidationException("The category " + field + " must be informed");
		}
	}

	private void checkProduct(ProductRequest request) {
		if (org.springframework.util.ObjectUtils.isEmpty(request.getName())) {
			throw new ValidationException("The product name was not informed.");
		}

		if (org.springframework.util.ObjectUtils.isEmpty(request.getCategoryId())) {
			throw new ValidationException("The category ID was not informed.");
		}

		if (org.springframework.util.ObjectUtils.isEmpty(request.getSupplierId())) {
			throw new ValidationException("The supplier ID was not informed.");
		}

		if (org.springframework.util.ObjectUtils.isEmpty(request.getQtAvailable())) {
			throw new ValidationException("The quantity available was not informed.");
		}

		if (request.getQtAvailable() <= ZERO) {
			throw new ValidationException("The quantity available should not be less or equal to zero.");
		}
	}
}
