package com.alpha.app.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alpha.app.DTO.CategoryDTO;
import com.alpha.app.Entity.Category;
import com.alpha.app.Entity.Product;
import com.alpha.app.Exception.ResourceNotFoundException;
import com.alpha.app.Repositiory.CategoryRepositiory;
import com.alpha.app.Repositiory.ProductRepositiory;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	private CategoryRepositiory cateRepo;

	@Autowired
	private ProductRepositiory prodRepo;
	
	@Autowired
	private ModelMapper mapper;

	// For Image set
	@Value("${content.upload.folder}")
	private String folderName;

	@PostConstruct
	public void myInit() {
		System.out.println("in myInit " + folderName);
		// chk of folder exists --o.w create one!
		File path = new File(folderName);
		if (!path.exists()) {
			path.mkdirs();
		} else
			System.out.println("folder alrdy exists....");
	}

	@Override
	public List<Category> getCategoryList() {
		// To get all categories and send as List
		return cateRepo.findAll();
	}

//	@Override
//	public ResponseEntity<String> addNewCategoryRecord(CategoryDTO newCate) {
//		// To add new category obj
//		try {
//		Category catObj = mapper.map(newCate, Category.class);
//		catObj.setCateCreatedOn(LocalDate.now());
//		
//		cateRepo.save(catObj);
//		return new ResponseEntity<String>("New Category added successfully", HttpStatus.CREATED);
//		}catch (ResourceNotFoundException e) {
//			// TODO: handle exception
//			return new ResponseEntity<String>("Error!!! Category Not added", HttpStatus.BAD_REQUEST);
//		}
//
//	}

	@Override
	public ResponseEntity<String> updateCategoryDetails(Long cateId, String updtName, MultipartFile updtImageFile,Boolean isActiveStatus)
			throws IOException {
		// TODO Auto-generated method stub
		Category oldCate = cateRepo.findById(cateId)
				.orElseThrow(() -> new ResourceNotFoundException("Category Not found"));
		oldCate.setCateName(updtName);
		// To set which date category was updated
		oldCate.setCateLastUpdatedOn(LocalDate.now());
		oldCate.setActive(isActiveStatus);
			if (oldCate.getCateImageUrl() != null) {
				Path path = Paths.get(folderName + File.separator + oldCate.getCateImageUrl());
				Files.delete(path);
				System.out.println("Old Image Deleted !!!");
			}
			if (!updtImageFile.isEmpty()) {
				long currentTimeMillis = System.currentTimeMillis();
				String targetPath = folderName + File.separator + currentTimeMillis+".jpg";
				Files.copy(updtImageFile.getInputStream(), Paths.get(targetPath), StandardCopyOption.REPLACE_EXISTING);
				
				oldCate.addImageToCategory(currentTimeMillis+".jpg");
				System.out.println("New Image Inserted !!!");
			}
		
		cateRepo.save(oldCate);

		return new ResponseEntity<String>("Category has been Updated!!!", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> addCateWithImage(String name, MultipartFile imageFile) throws IOException {
		// TODO Auto-generated method stub

		if(cateRepo.findByCateName(name).isPresent())
		{
			throw new DuplicateKeyException("Category "+name+" already present ");
		}
		
		Category addCat = new Category();
		addCat.setCateCreatedOn(LocalDate.now());
		addCat.setCateName(name);
		

		if (!imageFile.isEmpty()) {
			long currentTimeMillis = System.currentTimeMillis();
			String targetPath = folderName + File.separator + currentTimeMillis+".jpg";
//		System.out.println("Target path is: "+targetPath);
			// copy image file contents to the specified path
			Files.copy(imageFile.getInputStream(), Paths.get(targetPath), StandardCopyOption.REPLACE_EXISTING);
//		addCat.addImageToCategory(targetPath);
			addCat.addImageToCategory(currentTimeMillis+".jpg");
		}

		cateRepo.save(addCat);
		return new ResponseEntity<String>("New Category added successfully ", HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<String> deleteCategoryById(Long cateId) throws IOException {
		Category cateObj = cateRepo.findById(cateId)
				.orElseThrow(() -> new ResourceNotFoundException("Category Not found"));
		System.out.println("Inside cate dele service");
		//Find product which assoicated with deleted cateId
		List<Product> prodList= prodRepo.findByCategory(cateObj.getCateName());
		System.out.println("ProdList is "+prodList);
		if(!prodList.isEmpty())
		{
			return new ResponseEntity<String>("Errro!!! "+cateObj.getCateName()+" not deleted due to products already present in this section. ", HttpStatus.BAD_REQUEST);
		}
		
		if (cateObj.getCateImageUrl() != null) {
			
			Path path = Paths.get(folderName + File.separator + cateObj.getCateImageUrl());
			Files.delete(path);
			System.out.println("Category Image Deleted !!!");
		}
		cateRepo.deleteById(cateId);

		return new ResponseEntity<String>("Category Deleted successfully!!!", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> updateCategoryNameOnly(Long cateId, String updtName,Boolean isActiveStatus) {
		Category oldCate = cateRepo.findById(cateId)
				.orElseThrow(() -> new ResourceNotFoundException("Category Not found"));
		oldCate.setCateName(updtName);
		oldCate.setActive(isActiveStatus);
		// To set which date category was updated
		oldCate.setCateLastUpdatedOn(LocalDate.now());
		cateRepo.save(oldCate);

		return new ResponseEntity<String>("Category has been Updated!!!", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<Category>> allActiveCategories() {
		List<Category> activeCate = cateRepo.findByIsActive();
		return new ResponseEntity<List<Category>>(activeCate,HttpStatus.OK);
	}

}