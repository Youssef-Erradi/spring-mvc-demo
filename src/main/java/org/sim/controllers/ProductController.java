package org.sim.controllers;

import java.io.IOException;

import javax.validation.Valid;

import org.apache.tomcat.util.codec.binary.Base64;
import org.sim.dao.CategoryRepository;
import org.sim.dao.ProductRepository;
import org.sim.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
	@Autowired private CategoryRepository categoryRepository;
	private final String folderName = "products";
	
	@GetMapping("")
	public String index(Model model) {
		model.addAttribute("products", repository.findAll());
		return folderName+"/list";
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("categories", categoryRepository.findAll());
		return folderName+"/form";
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
		if(!repository.existsById(id)) {
			redirectAttributes.addFlashAttribute("message", "No Product found with an ID of "+id);
			return "redirect:/"+folderName;
		}
		model.addAttribute("product", repository.getById(id));
		model.addAttribute("categories", categoryRepository.findAll());
		return folderName+"/form";
	}
	
	@PostMapping("/save")
	public String save(@Valid Product product, BindingResult bindingResult, MultipartFile imageFile,
					   Model model,RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("message", "There is some errors during validation");
			model.addAttribute("product", product);
			model.addAttribute("categories", categoryRepository.findAll());
			return folderName+"/form";
		}
		
		if(!imageFile.isEmpty())
			try {
				product.setImage(Base64.encodeBase64String(imageFile.getBytes()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		repository.save(product);
		return "redirect:/"+folderName;
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
		String message = "The Product has been deleted successfully";
		if(!repository.existsById(id)) {
			message = "No Product found with an ID of "+id;
		} else {
			repository.delete(repository.getById(id));
		}
		redirectAttributes.addFlashAttribute("message", message);
		return "redirect:/"+folderName;
	}
}
