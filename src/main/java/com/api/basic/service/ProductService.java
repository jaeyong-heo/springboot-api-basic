package com.api.basic.service;

import com.api.basic.dto.ProductDTO;


public interface ProductService {

	ProductDTO getProduct(Long number);
	
	ProductDTO saveProduct(ProductDTO productDTO);
	
	ProductDTO changeProductName(Long number, String name) throws Exception;
	
	void deleteProduct(Long number) throws Exception;
}
