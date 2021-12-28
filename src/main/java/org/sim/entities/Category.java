package org.sim.entities;

import java.util.List;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Accessors(chain = true)
public class Category implements Serializable{
	private static final long serialVersionUID = 6270701829894111191L;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name, description;
	@OneToMany(mappedBy="category", fetch=FetchType.EAGER)
	private List<Product> products;
}
