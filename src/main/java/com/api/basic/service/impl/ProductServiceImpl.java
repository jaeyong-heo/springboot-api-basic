package com.api.basic.service.impl;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.basic.dto.ProductDTO;
import com.api.basic.repository.ProductRepository;
import com.api.basic.service.ProductService;
import com.api.basic.entity.Product;



@Service
public class ProductServiceImpl implements ProductService{

	private final ProductRepository productRepository;
	private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Autowired
	public ProductServiceImpl(ProductRepository productRepository)
	{
		this.productRepository = productRepository;
	}
	
	@Override
	public ProductDTO changeProductName(Long number, String name) throws Exception {
		Product foundProduct = productRepository.findById(number).get();
		foundProduct.setName(name);
		Product changedProduct = productRepository.save(foundProduct);
		
		ProductDTO ProductDTO = new ProductDTO();
		ProductDTO.setNumber(changedProduct.getNumber());
		ProductDTO.setName(changedProduct.getName());
		ProductDTO.setPrice(changedProduct.getPrice());
		ProductDTO.setStock(changedProduct.getStock());
		
		return ProductDTO;
	}
	@Override
	public void deleteProduct(Long number) throws Exception {
		productRepository.deleteById(number);
	}
	
	@Override
	public ProductDTO getProduct(Long number) {
		logger.info("[getProduct] input number : {}", number);
		Product product = productRepository.findById(number).get();
		logger.info("[getProduct] product number : {}, name : {} ", product.getNumber(), product.getName());
		
		ProductDTO ProductDTO = new ProductDTO();
		ProductDTO.setNumber(product.getNumber());
		ProductDTO.setName(product.getName());
		ProductDTO.setPrice(product.getPrice());
		ProductDTO.setStock(product.getStock());
		
		return ProductDTO;
	}
	
	@Override
	public ProductDTO saveProduct(ProductDTO productDTO) {
		logger.info("[saveProduct] productDTO : {}", productDTO.toString());
		Product product = new Product();
		product.setName(productDTO.getName());
		product.setPrice(productDTO.getPrice());
		product.setStock(productDTO.getStock());
		product.setCreatedAt(LocalDateTime.now());
		product.setUpdatedAt(LocalDateTime.now());
		
//		Product savedProduct = productDAO.insertProduct(product);
		Product savedProduct = productRepository.save(product);
		logger.info("[saveProduct] saveProduct : {}", savedProduct);
		
		ProductDTO ProductDTO = new ProductDTO();
		ProductDTO.setNumber(savedProduct.getNumber());
		ProductDTO.setName(savedProduct.getName());
		ProductDTO.setPrice(savedProduct.getPrice());
		ProductDTO.setStock(savedProduct.getStock());
		
		
		return ProductDTO;
	}
}
