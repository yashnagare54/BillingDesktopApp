package com.alpha.app.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alpha.app.DTO.CategoryDTO;
import com.alpha.app.Entity.Category;
import com.alpha.app.Service.ICategoryService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	private ICategoryService cateService;
	
	
	// Send List of all categories
	@GetMapping("/allcategories")
	List<Category> getAllCategories()
	{
		return cateService.getCategoryList();
	}
	
//	@PostMapping(value = "/add_category")
//	ResponseEntity<String> addNewCategory(@RequestBody CategoryDTO newCate)
//	{
//		return cateService.addNewCategoryRecord(newCate);
//	}
	
	@PostMapping(value = "/add_catewithimg", consumes ={"multipart/form-data"})
	ResponseEntity<String> addCateWithImage(@RequestParam("cateName") String name,@RequestParam("imageName") MultipartFile imageFile) throws IllegalStateException, IOException
	{
//		System.out.println(imageFile);
//		System.out.println("in upload img " + name + " " + imageFile.getOriginalFilename());
		return cateService.addCateWithImage(name, imageFile);
		
	}
	
	@PutMapping(value="/update/{cateId}", consumes ={"multipart/form-data"})
	ResponseEntity<String> updateCategory(@PathVariable Long cateId,@RequestParam("cateName") String updtName,@RequestParam("isActive") Boolean isActiveStatus,@RequestParam("imageName") MultipartFile updtImageFile) throws IOException
	{
		if (!updtImageFile.isEmpty()) {
		return cateService.updateCategoryDetails(cateId,updtName,updtImageFile,isActiveStatus);
		}
		return cateService.updateCategoryNameOnly(cateId,updtName,isActiveStatus);
		
	}
	
	@DeleteMapping("/delete/{cateId}")
	ResponseEntity<String> deleteCategory(@PathVariable Long cateId) throws IOException
	{
		return cateService.deleteCategoryById(cateId);
	}
	
	@GetMapping("/active-categories")
	ResponseEntity<List<Category>> displayActiveCategories()
	{
		return cateService.allActiveCategories();
	}
	
	
}
