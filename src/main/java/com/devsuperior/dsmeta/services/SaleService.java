package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleDTO;
import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}
	
	public Page<SaleDTO> getReport(String minDateStr, String maxDateStr, String name, Pageable pageable) {
		
		String pattern = "yyyy-MM-dd";
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern(pattern);
		
		LocalDate maxDateE = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		LocalDate minDateE = maxDateE.minusYears(1L);
				
		if (minDateStr.trim().isEmpty()) {
			minDateStr = String.valueOf(minDateE);
		}
		
		if (maxDateStr.trim().isEmpty()) {
			maxDateStr = String.valueOf(maxDateE);
		}
		
		LocalDate minDate = LocalDate.parse(minDateStr, fmt);
		LocalDate maxDate = LocalDate.parse(maxDateStr, fmt);	
		
		Page<SaleDTO> result = repository.getReport(minDate, maxDate, name, pageable);
		return result;
	}
}