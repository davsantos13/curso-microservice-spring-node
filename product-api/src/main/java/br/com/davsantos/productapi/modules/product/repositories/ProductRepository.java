package br.com.davsantos.productapi.modules.product.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.davsantos.productapi.modules.product.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findByNameIgnoreCaseContaining(String name);

	List<Product> findByCategoryId(Long id);

	List<Product> findBySupplierId(Long id);

	Boolean existsBySupplierId(Long id);

	Boolean existsByCategoryId(Long id);
}
