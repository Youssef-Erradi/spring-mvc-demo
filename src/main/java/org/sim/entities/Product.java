package org.sim.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity @Table(name = "products")
@Data @AllArgsConstructor @NoArgsConstructor @Accessors(chain = true)
public class Product implements Serializable{
	private static final long serialVersionUID = -5721712796550921120L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank @Size(min = 10, max = 50)
	private String name;
	
	@NotBlank @Size(min = 10, max = 255)
	private String description;
	
	@Lob
	private String image;
	
	@Min(10)
	private double price;
	
	@ManyToOne
	private Category category;
	
	public String toString() {
		return id+"-"+name;
	}
}
