package com.alpha.app.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.alpha.app.DTO.CategoryDTO;
import com.alpha.app.Entity.Category;

public interface ICategoryService {

	List<Category> getCategoryList();

//	ResponseEntity<String> addNewCategoryRecord(CategoryDTO newCate);

	ResponseEntity<String> updateCategoryDetails(Long cateId,String updtName, MultipartFile updtImageFile,Boolean isActiveStatus) throws IOException;

	ResponseEntity<String> deleteCategoryById(Long cateId) throws IOException;

	// Cate wth Image
	ResponseEntity<String> addCateWithImage(String name,MultipartFile imageFile) throws IllegalStateException, IOException;

	ResponseEntity<String> updateCategoryNameOnly(Long cateId, String updtName,Boolean isActiveStatus);

	ResponseEntity<List<Category>> allActiveCategories();

	

}
