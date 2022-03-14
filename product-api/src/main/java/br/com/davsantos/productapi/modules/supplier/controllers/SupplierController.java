package br.com.davsantos.productapi.modules.supplier.controllers;

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
import br.com.davsantos.productapi.modules.supplier.dtos.SupplierRequest;
import br.com.davsantos.productapi.modules.supplier.dtos.SupplierResponse;
import br.com.davsantos.productapi.modules.supplier.services.SupplierService;

@RestController
@RequestMapping(value = "/api/suppliers")
public class SupplierController {

	@Autowired
	private SupplierService service;

	@PostMapping
	public SupplierResponse save(@RequestBody SupplierRequest request) {
		return service.save(request);
	}

	@GetMapping
	public List<SupplierResponse> findAll() {
		return service.findAll();
	}

	@GetMapping("{id}")
	public SupplierResponse findById(@PathVariable Long id) {
		return service.findByIdResponse(id);
	}

	@PutMapping("{id}")
	public SupplierResponse findById(@RequestBody SupplierRequest request, @PathVariable Long id) {
		return service.update(request, id);
	}

	@GetMapping("/name/{name}")
	public List<SupplierResponse> findByDescription(@PathVariable String name) {
		return service.findByName(name);
	}

	@DeleteMapping("{id}")
	public SuccessResponse delete(@PathVariable Long id) {
		return service.delete(id);
	}

}
