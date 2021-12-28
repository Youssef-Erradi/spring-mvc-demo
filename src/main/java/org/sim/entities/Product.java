package org.sim.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Accessors(chain = true)
public class Product implements Serializable{
	private static final long serialVersionUID = -5721712796550921120L;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String libelle, description, photo;
	private double price;
	@ManyToOne
	private Category category;
}
