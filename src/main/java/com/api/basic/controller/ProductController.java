package com.api.basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.basic.dto.ProductDTO;
import com.api.basic.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	private final ProductService productService;
	
	@Autowired
	public ProductController(ProductService productService)
	{
		this.productService = productService;
	}
	
	@GetMapping
	public ResponseEntity<ProductDTO> getProduct(Long number)
	{
		ProductDTO productDto = productService.getProduct(number);
		
		return ResponseEntity.status(HttpStatus.OK).body(productDto);
	}
	
	@PostMapping
	public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO)
	{
		ProductDTO productResponseDTO = productService.saveProduct(productDTO);
		
		return ResponseEntity.status(HttpStatus.OK).body(productResponseDTO);
	}
	
	@PutMapping
	public ResponseEntity<ProductDTO> changeProductName(
			@RequestBody ProductDTO productDTO) throws Exception
	{
		ProductDTO productResponseDto = productService.changeProductName(productDTO.getNumber(), productDTO.getName());
		return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
	}
	
	@DeleteMapping
	public ResponseEntity<String> deleteProduct(Long number) throws Exception
	{
		productService.deleteProduct(number);
		return ResponseEntity.status(HttpStatus.OK).body("삭제 완료");
	}
}
