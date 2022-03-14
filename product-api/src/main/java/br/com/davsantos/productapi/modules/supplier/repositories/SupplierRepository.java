package br.com.davsantos.productapi.modules.supplier.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.davsantos.productapi.modules.supplier.models.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
	List<Supplier> findByNameIgnoreCaseContaining(String name);
}
