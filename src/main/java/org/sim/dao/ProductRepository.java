package org.sim.dao;

import java.util.List;

import org.sim.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	public List<Product> findByDescriptionContaining(String description);
	public List<Product> findByNameContaining(String name);
	public List<Product> findByPriceBetween(double start, double end);
}
