package br.com.davsantos.productapi.modules.supplier.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import br.com.davsantos.productapi.modules.supplier.dtos.SupplierRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "suppliers")
public class Supplier {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "tx_name", nullable = false)
	private String name;

	public static Supplier of(SupplierRequest request) {
		var response = new Supplier();
		BeanUtils.copyProperties(request, response);

		return response;
	}
}
