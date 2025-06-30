package com.alpha.app.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.alpha.app.DTO.ProductDTO;
import com.alpha.app.Entity.Product;

public interface IProductService {

	List<Product> allProductList();

	ResponseEntity<?> addNewProduct(Long cateId, String prodName, double prodPrice, MultipartFile imageFile) throws IOException;

	ResponseEntity<String> deleteProductById(Long prodId) throws IOException;

	ResponseEntity<?> updateProductDetails(Long prodId, String updtProdName, double updtProdPrice,Boolean isActiveStatus,
			MultipartFile updtProdImageFile) throws IOException;

	

	

}
