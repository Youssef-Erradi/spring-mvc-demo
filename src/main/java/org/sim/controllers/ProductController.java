package org.sim.controllers;

import java.io.IOException;

import org.apache.tomcat.util.codec.binary.Base64;
import org.sim.dao.ProductRepository;
import org.sim.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/products")
public class ProductController {
	@Autowired private ProductRepository repository;
	private final String folderName = "products";
	
	@GetMapping("")
	public String index(Model model) {
		model.addAttribute("products", repository.findAll());
		return folderName+"/list";
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("product", new Product());
		return folderName+"/form";
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
		if(!repository.existsById(id)) {
			redirectAttributes.addAttribute("message", "No Product found with an ID of "+id);
			return "redirect:/"+folderName;
		}
		model.addAttribute("product", repository.getById(id));
		return folderName+"/form";
	}
	
	@PostMapping("/save")
	public String save(MultipartFile image, Product product, Model model) {
//		Product p = new Product();
//		String state = "sucess";
//		try {
//			p.setImage(Base64.encodeBase64String(image.getBytes()));
//		} catch (IOException e) {
//			state = e.getMessage();
//			e.printStackTrace();
//		}
//		model.addAttribute("state", state);
//		productRepository.save(p);
		return "redirect:/"+folderName;
	}
}
