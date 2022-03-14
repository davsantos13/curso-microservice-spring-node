package br.com.davsantos.productapi.modules.category.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import br.com.davsantos.productapi.modules.category.dtos.CategoryRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "categories")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "tx_description", nullable = false)
	private String description;

	public static Category of(CategoryRequest request) {
		var response = new Category();
		BeanUtils.copyProperties(request, response);

		return response;
	}
}
