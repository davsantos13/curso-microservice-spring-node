package br.com.davsantos.productapi.modules.product.dtos;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ProductRequest {

	private String name;
	private Long supplierId;
	private Long categoryId;
	private Integer qtAvailable;
	@JsonProperty("created_at")
	private LocalDateTime createdAt;
}
