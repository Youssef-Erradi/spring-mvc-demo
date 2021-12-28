package org.sim.entities;

import java.util.List;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity @Table(name = "categories")
@Data @AllArgsConstructor @NoArgsConstructor @Accessors(chain = true)
public class Category implements Serializable{
	private static final long serialVersionUID = 6270701829894111191L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank @Size(min = 5, max = 50)
	private String name;
	
	@NotBlank @Size(min = 10, max = 255)
	private String description;
	
	@OneToMany(mappedBy="category", fetch=FetchType.EAGER)
	private List<Product> products;
}
