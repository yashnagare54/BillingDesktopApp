package com.alpha.app.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.alpha.app.DTO.ProductDTO;
import com.alpha.app.Entity.Product;
import com.alpha.app.Service.IProductService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/product")
public class ProductController {

	@Autowired
	private IProductService prodService;
	
	@GetMapping("/allproducts")
	List<Product> getAllProducts()
	{
		
		return prodService.allProductList();
	}
	
	@PostMapping(value="/category/{cateId}/add_product", consumes ={"multipart/form-data"})
	ResponseEntity<?> addNewProduct(@PathVariable Long cateId, 
			@RequestParam("prodName")String prodName,@RequestParam("prodPrice")double prodPrice,
			@RequestParam("imageName") MultipartFile imageFile ) throws IOException
	{
		return prodService.addNewProduct(cateId,prodName,prodPrice,imageFile);
	}
	
	@PutMapping("/update_product/{prodId}")
	ResponseEntity<?> updateProduct(@PathVariable Long prodId, 
			@RequestParam("prodName")String updtProdName,@RequestParam("prodPrice")double updtProdPrice,
			@RequestParam("isActive") Boolean isActiveStatus,
			@RequestParam("imageName") MultipartFile updtProdImageFile ) throws IOException
	{
		return prodService.updateProductDetails(prodId,updtProdName,updtProdPrice,isActiveStatus,updtProdImageFile);
	}
	
	@DeleteMapping("/delete/{prodId}")
	ResponseEntity<String> deleteProduct(@PathVariable Long prodId) throws IOException
	{
		return prodService.deleteProductById(prodId);
	}
}
