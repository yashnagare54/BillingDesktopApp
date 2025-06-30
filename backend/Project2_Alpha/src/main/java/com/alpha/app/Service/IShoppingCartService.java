package com.alpha.app.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.alpha.app.DTO.AddProductDTO;
import com.alpha.app.DTO.RemoveProductFromCartDTO;
import com.alpha.app.Entity.CartProducts;
import com.alpha.app.Exception.UserHandlingException;

public interface IShoppingCartService {

	void addProductToCart(Long custId, Long prodId, int quantity) throws UserHandlingException;

//	List<CartProducts> getCartProductList(long custId);

//	void removeSingleProduct(RemoveProductFromCartDTO removeProd);

	void addAllProductsToCartTest(List<AddProductDTO> addProd);

}
