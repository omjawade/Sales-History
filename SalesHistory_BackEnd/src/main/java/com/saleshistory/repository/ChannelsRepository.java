package com.saleshistory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.saleshistory.entity.Channels;
import com.saleshistory.entity.Products;

@Repository
public interface ChannelsRepository extends JpaRepository<Channels, Integer> {

	@Query("SELECT SUM(s.amountSold) FROM Sales s WHERE s.channelId.channelId = :channelId")
	Double calculateRevenueByChannel(@Param("channelId") Integer channelId);

	@Query("SELECT COUNT(s) FROM Sales s WHERE s.channelId.channelId = :channelId")
	Integer calculateAggregateSaleByChannel(@Param("channelId") Integer channelId);

	@Query("SELECT s.prodId FROM Sales s WHERE s.channelId.channelId = :channelId "
			+ "GROUP BY s.prodId ORDER BY COUNT(s) DESC limit 1")
	List<Products> findMostSoldProductsByChannel(@Param("channelId") Integer channelId);
}
