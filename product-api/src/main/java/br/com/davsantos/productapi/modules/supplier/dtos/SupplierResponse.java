package br.com.davsantos.productapi.modules.supplier.dtos;

import org.springframework.beans.BeanUtils;

import br.com.davsantos.productapi.modules.supplier.models.Supplier;
import lombok.Data;

@Data
public class SupplierResponse {

	private Long id;
	private String name;

	public static SupplierResponse of(Supplier supplier) {
		var response = new SupplierResponse();

		BeanUtils.copyProperties(supplier, response);

		return response;
	}
}
