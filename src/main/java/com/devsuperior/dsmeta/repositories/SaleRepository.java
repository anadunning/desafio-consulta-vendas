package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsmeta.dto.SaleDTO;
import com.devsuperior.dsmeta.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
	
//	@Query("SELECT new com.devsuperior.dsmeta.dto.SaleDTO(obj.id, obj.date, obj.amount, obj.name) FROM Sale obj"
//			+ " WHERE obj.date BETWEEN :start AND :end "
//			+ " AND UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :name, '%'))"
//			+ "GROUP BY obj.seller.name")
//	List<SaleDTO> getReport(LocalDate start, LocalDate end, String name);
	
	@Query("SELECT new com.devsuperior.dsmeta.dto.SaleDTO(obj.id, obj.date, obj.amount, obj.seller.name) FROM Sale obj"
			+ " WHERE UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :name, '%' ))")
	List<SaleDTO> getReport(String name);

	
	
	
//	@Query("SELECT new com.devsuperior.dsmeta.dto.SaleDTO(obj.sale.id, obj.sale.amount, obj.seller.name) FROM Sale obj"
//			+ " WHERE UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :name, '%' ))"
//			+ " GROUP BY obj.seller.name")
//	List<SaleDTO> getReport(String start, String end, String name);
	
			
		
	

}
