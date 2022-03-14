package br.com.davsantos.productapi.modules.product.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import br.com.davsantos.productapi.modules.category.models.Category;
import br.com.davsantos.productapi.modules.product.dtos.ProductRequest;
import br.com.davsantos.productapi.modules.supplier.models.Supplier;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "tx_name", nullable = false)
	private String name;

	@Column(name = "nr_qt_available")
	private Integer qtAvailable;

	@ManyToOne
	@JoinColumn(name = "fk_categories", nullable = false)
	private Category category;

	@ManyToOne
	@JoinColumn(name = "fk_suppliers", nullable = false)
	private Supplier supplier;

	@Column(name = "dt_created_at", updatable = false)
	private LocalDateTime createdAt;

	@PrePersist
	public void prePersist() {
		createdAt = LocalDateTime.now();
	}

	public static Product of(ProductRequest request, Supplier supplier, Category category) {
		return Product.builder()
				.name(request.getName())
				.qtAvailable(request.getQtAvailable())
				.category(category)
				.supplier(supplier).build();
	}
}
