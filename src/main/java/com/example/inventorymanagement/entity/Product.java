package com.example.inventorymanagement.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private LocalDate stockImportDate;
    private LocalDate stockExportDate;
    private String shiftManagerName;
    private Long shiftManagerId;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    @JsonBackReference
    private Supplier supplier;
    
    public Product() {
		// TODO Auto-generated constructor stub
	}

    // getters and setters
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public LocalDate getStockImportDate() {
		return stockImportDate;
	}

	public void setStockImportDate(LocalDate stockImportDate) {
		this.stockImportDate = stockImportDate;
	}

	public LocalDate getStockExportDate() {
		return stockExportDate;
	}

	public void setStockExportDate(LocalDate stockExportDate) {
		this.stockExportDate = stockExportDate;
	}

	public String getShiftManagerName() {
		return shiftManagerName;
	}

	public void setShiftManagerName(String shiftManagerName) {
		this.shiftManagerName = shiftManagerName;
	}

	public Long getShiftManagerId() {
		return shiftManagerId;
	}

	public void setShiftManagerId(Long shiftManagerId) {
		this.shiftManagerId = shiftManagerId;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

      
}
