package br.com.davsantos.productapi.modules.product.dtos;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.davsantos.productapi.modules.category.dtos.CategoryResponse;
import br.com.davsantos.productapi.modules.product.models.Product;
import br.com.davsantos.productapi.modules.supplier.dtos.SupplierResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

	private Long id;
	private String name;
	private SupplierResponse supplier;
	private CategoryResponse category;
	private Integer qtAvailable;
	
	@JsonProperty("created_at")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime createdAt;

	public static ProductResponse of(Product product) {
		return ProductResponse
				.builder()
				.id(product.getId())
				.name(product.getName())
				.qtAvailable(product.getQtAvailable())
				.createdAt(product.getCreatedAt())
				.supplier(SupplierResponse.of(product.getSupplier()))
				.category(CategoryResponse.of(product.getCategory()))
				.build();
	}
}
