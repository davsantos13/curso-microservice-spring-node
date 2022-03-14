package br.com.davsantos.productapi.modules.product.controllers;

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
import br.com.davsantos.productapi.modules.product.dtos.ProductRequest;
import br.com.davsantos.productapi.modules.product.dtos.ProductResponse;
import br.com.davsantos.productapi.modules.product.services.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService service;

	@PostMapping
	public ProductResponse save(@RequestBody ProductRequest request) {

		return service.save(request);
	}

	@GetMapping
	public List<ProductResponse> findAll() {
		return service.findAll();
	}

	@GetMapping("{id}")
	public ProductResponse findById(@PathVariable Long id) {
		return service.findByIdResponse(id);
	}

	@PutMapping("{id}")
	public ProductResponse update(@RequestBody ProductRequest request, @PathVariable Long id) {
		return service.update(request, id);
	}

	@GetMapping("/name/{name}")
	public List<ProductResponse> findByName(@PathVariable String name) {
		return service.findByName(name);
	}

	@GetMapping("/category/{categoryId}")
	public List<ProductResponse> findByCategoryId(@PathVariable Long categoryId) {
		return service.findByCategoryId(categoryId);
	}

	@GetMapping("/supplier/{supplierId}")
	public List<ProductResponse> findBySupplierId(@PathVariable Long supplierId) {
		return service.findBySupplierId(supplierId);
	}

	@DeleteMapping("{id}")
	public SuccessResponse delete(@PathVariable Long id) {
		return service.delete(id);
	}

}
