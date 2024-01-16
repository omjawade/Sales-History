package com.saleshistory.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.saleshistory.dto.ChannelSoldProductDTO;
import com.saleshistory.dto.DuplicateProductDTO;
import com.saleshistory.entity.Products;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Integer> {

	List<Products> findByProdStatus(String prodStatus);

	@Query("SELECT p from Products p WHERE p.prodCategory LIKE %:category%")
	List<Products> findByProdCategory(@Param("category") String category);

	@Query("SELECT p from Products p WHERE p.prodSubcategory LIKE %:prodSubcategory%")
	List<Products> findByProdSubcategory(@Param("prodSubcategory") String prodSubcategory);

	List<Products> findBySupplierId_Id(Integer supplierId);

	@Query("SELECT p.prodName as prodName, COUNT(p.prodId) as count " + "FROM Products p " + "GROUP BY p.prodName "
			+ "HAVING COUNT(p.prodId) > 1")
	List<DuplicateProductDTO> findDuplicateProducts();

	@Query(value = "SELECT ch.channel_desc AS channelDesc, p.prod_id AS prodId, p.prod_name AS prodName, p.prod_desc AS prodDesc, "
			+ "p.prod_category AS prodCategory, p.prod_list_price AS prodListPrice, c.cust_id AS custId, c.cust_first_name AS custFirstName, "
			+ "c.cust_last_name AS custLastName " + "FROM channels ch "
			+ "INNER JOIN sales s ON ch.channel_id = s.channel_id " + "INNER JOIN customers c ON s.cust_id = c.cust_id "
			+ "INNER JOIN products p ON s.prod_id = p.prod_id "
			+ "WHERE ch.channel_desc LIKE CONCAT('%', ?1, '%')", nativeQuery = true)
	List<ChannelSoldProductDTO> findSoldProductsByChannel(String channelDesc);

	List<Products> findAll(Sort sort);

	@Query(value = "SELECT * from sales_history.products p Where p.prod_status LIKE '%SOLD%';", nativeQuery = true)

	List<Products> findByProdStatusSold();

}
