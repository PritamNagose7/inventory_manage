package com.example.inventorymanagement.repository;

import com.example.inventorymanagement.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
