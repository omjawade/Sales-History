package com.saleshistory.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saleshistory.entity.Supplier;

public interface SuppliersRepository extends JpaRepository<Supplier, Integer> {

	Optional<Supplier> findById(Integer supplierId);

}
