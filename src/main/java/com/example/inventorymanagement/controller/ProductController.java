package com.example.inventorymanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.inventorymanagement.entity.Product;
import com.example.inventorymanagement.entity.Supplier;
import com.example.inventorymanagement.exception.ResourceNotFoundException;
import com.example.inventorymanagement.repository.ProductRepository;
import com.example.inventorymanagement.repository.SupplierRepository;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private SupplierRepository supplierRepository;

	@GetMapping
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id) {
		Product product = productRepository.findById(id).orElse(null);
		if (product == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(product);
	}

	@PostMapping
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		Supplier supplier = supplierRepository.findById(product.getSupplier().getId()).orElse(null);
		if (supplier == null) {
			return ResponseEntity.badRequest().body(null);
		}
		product.setSupplier(supplier);
		return ResponseEntity.ok(productRepository.save(product));
	}

	@PutMapping("/{id}")
	public Product updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found"));

		product.setName(productDetails.getName());
		product.setDescription(productDetails.getDescription());
		product.setPrice(productDetails.getPrice());
		product.setStock(productDetails.getStock());
		product.setStockImportDate(productDetails.getStockImportDate());
		product.setStockExportDate(productDetails.getStockExportDate());
		product.setShiftManagerName(productDetails.getShiftManagerName());
		product.setShiftManagerId(productDetails.getShiftManagerId());
		product.setSupplier(productDetails.getSupplier());

		return productRepository.save(product);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found"));

		productRepository.delete(product);

		return ResponseEntity.ok().build();
	}
}
