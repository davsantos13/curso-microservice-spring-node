package br.com.davsantos.productapi.modules.supplier.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.davsantos.productapi.config.SuccessResponse;
import br.com.davsantos.productapi.config.exceptions.ValidationException;
import br.com.davsantos.productapi.modules.product.services.ProductService;
import br.com.davsantos.productapi.modules.supplier.dtos.SupplierRequest;
import br.com.davsantos.productapi.modules.supplier.dtos.SupplierResponse;
import br.com.davsantos.productapi.modules.supplier.models.Supplier;
import br.com.davsantos.productapi.modules.supplier.repositories.SupplierRepository;

@Service
public class SupplierService {

	@Autowired
	private SupplierRepository repository;

	@Autowired
	private ProductService productService;

	public SupplierResponse save(SupplierRequest request) {
		checkInformedData(request.getName(), "name");

		var category = repository.save(Supplier.of(request));
		return SupplierResponse.of(category);
	}

	public SupplierResponse update(SupplierRequest request, Long id) {
		checkInformedData(request.getName(), "name");
		checkInformedData(id, "id");

		var supplier = Supplier.of(request);
		supplier.setId(id);

		supplier = repository.save(supplier);
		return SupplierResponse.of(supplier);
	}

	public List<SupplierResponse> findAll() {
		return repository.findAll().stream().map(s -> SupplierResponse.of(s)).collect(Collectors.toList());
	}

	public Supplier findById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new ValidationException("There's no supplier for the given ID"));
	}

	public SupplierResponse findByIdResponse(Long id) {
		return SupplierResponse.of(findById(id));
	}

	public List<SupplierResponse> findByName(String name) {
		checkInformedData(name, "name");
		return repository.findByNameIgnoreCaseContaining(name).stream().map(c -> SupplierResponse.of(c))
				.collect(Collectors.toList());
	}

	public SuccessResponse delete(Long id) {
		checkInformedData(id, "id");

		if (productService.existsBySupplierId(id)) {
			throw new ValidationException("You cannot delete this supplier because it's already defined by a product");
		}

		repository.deleteById(id);

		return SuccessResponse.create("The supplier was deleted!");
	}

	private void checkInformedData(Object data, String field) {
		if (org.springframework.util.ObjectUtils.isEmpty(data)) {
			throw new ValidationException("The supplier " + field + " must be informed");
		}
	}
}
