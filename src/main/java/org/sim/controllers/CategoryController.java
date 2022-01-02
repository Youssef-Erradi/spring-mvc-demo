package org.sim.controllers;

import java.util.List;

import javax.validation.Valid;

import org.sim.dao.CategoryRepository;
import org.sim.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/categories")
public class CategoryController {
	@Autowired private CategoryRepository repository;
	private final String folderName = "categories";
	
	@GetMapping("")
	public String index(@RequestParam(defaultValue = "") String description,
						@RequestParam(defaultValue = "") String name, Model model) {
		List<Category> categories = repository.findByDescriptionContaining(description);
		categories.retainAll(repository.findByNameContaining(name));
		model.addAttribute("categories",categories);
		return folderName+"/list";
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("category", new Category());
		return folderName+"/form";
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
		if(!repository.existsById(id)) {
			redirectAttributes.addAttribute("message", "No Category found with an ID of "+id);
			return "redirect:/"+folderName;
		}
		model.addAttribute("category", repository.getById(id));
		return folderName+"/form";
	}
	
	@PostMapping("/save")
	public String save(@Valid Category category, BindingResult bindingResult,
						Model model,RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("message", "There is some errors during validation");
			model.addAttribute("category", category);
			return folderName+"/form";
		}
		repository.save(category);
		redirectAttributes.addFlashAttribute("message", "The category has been saved successfully");
		return "redirect:/"+folderName;
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
		String message = "The category has been deleted successfully";
		if(!repository.existsById(id)) {
			message = "No Category found with an ID of "+id;
		} else {
			repository.delete(repository.getById(id));
		}
		redirectAttributes.addFlashAttribute("message", message);
		return "redirect:/"+folderName;
	}
}
