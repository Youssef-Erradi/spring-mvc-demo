package org.sim.dao;

import java.util.List;

import org.sim.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	public List<Category> findByDescriptionContaining(String description);
	public List<Category> findByNameContaining(String name);
}
