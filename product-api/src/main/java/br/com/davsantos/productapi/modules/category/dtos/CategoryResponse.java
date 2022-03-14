package br.com.davsantos.productapi.modules.category.dtos;

import org.springframework.beans.BeanUtils;

import br.com.davsantos.productapi.modules.category.models.Category;
import lombok.Data;

@Data
public class CategoryResponse {

	private Long id;
	private String description;

	public static CategoryResponse of(Category category) {
		var response = new CategoryResponse();

		BeanUtils.copyProperties(category, response);

		return response;
	}
}
