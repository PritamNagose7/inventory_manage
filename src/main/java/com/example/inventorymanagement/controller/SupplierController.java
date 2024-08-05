package com.example.inventorymanagement.controller;

import com.example.inventorymanagement.entity.Supplier;
import com.example.inventorymanagement.exception.ResourceNotFoundException;
import com.example.inventorymanagement.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {
	@Autowired
	private SupplierRepository supplierRepository;

	@GetMapping
	public List<Supplier> getAllSuppliers() {
		return supplierRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Supplier> getSupplierById(@PathVariable Long id) {
		Supplier supplier = supplierRepository.findById(id).orElse(null);
		if (supplier == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(supplier);
	}

	@PostMapping
	public Supplier createSupplier(@RequestBody Supplier supplier) {
		return supplierRepository.save(supplier);
	}

	@PutMapping("/{id}")
	public Supplier updateSupplier(@PathVariable Long id, @RequestBody Supplier supplierDetails) {
		Supplier supplier = supplierRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Supplier not found"));

		supplier.setName(supplierDetails.getName());
		supplier.setContactInfo(supplierDetails.getContactInfo());

		return supplierRepository.save(supplier);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteSupplier(@PathVariable Long id) {
		Supplier supplier = supplierRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Supplier not found"));

		supplierRepository.delete(supplier);

		return ResponseEntity.ok().build();
	}
}
