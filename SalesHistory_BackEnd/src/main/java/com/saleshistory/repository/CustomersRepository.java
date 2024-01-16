package com.saleshistory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.saleshistory.entity.Channels;
import com.saleshistory.entity.Customers;

@Repository
public interface CustomersRepository extends JpaRepository<Customers, Integer> {

	List<Customers> findByCustFirstName(String custFirstName);

	@Query("SELECT c from Customers c WHERE c.custCity LIKE %:custCity%")
	List<Customers> findByCustCity(String custCity);

	Boolean existsByCustEmail(String custEmail);

	@Query("SELECT c from Customers c WHERE c.custIncomeLevel LIKE :income%")
	List<Customers> findByCustIncomeLevel(@Param("income") String custIncomeLevel);

	@Query("SELECT c FROM Customers c WHERE c.custCreditLimit BETWEEN :start AND :end")
	List<Customers> getCustomersBetweenCreditLimit(@Param("start") Integer credStart, @Param("end") Integer credend);

	@Query("UPDATE Customers c SET c.custCreditLimit = :newLimit WHERE c.custId = :custId")
	Customers updateCustomerCreditLimit(@Param("newLimit") Integer newLimit, @Param("custId") Integer custId);

	@Query(value = "select p. * from sales_history.products p, sales_history.sales s "
			+ "where p.prod_id= s.prod_id AND s.cust_id= :custId", nativeQuery = true)
	List<Object[]> getProductsSoldToCustomer(@Param("custId") Integer custId);

	@Query("select ch from Channels ch, Sales s where ch.channelId = s.channelId.channelId AND "
			+ "s.custId.custId = :custId")
	List<Channels> getChannelsUsedByCustomer(@Param("custId") Integer custId);

}
