package net.kdigital.web_project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kdigital.web_project.dto.BycounImExPriceDTO;
import net.kdigital.web_project.entity.BycounImExPriceEntity;
import net.kdigital.web_project.repository.BycounImExPriceRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class BycounImExPriceService {

	private final BycounImExPriceRepository imexRepository;
	public List<BycounImExPriceDTO> bycounImExPrice(String country) {
		log.info("string나라{}", country);
		List<BycounImExPriceEntity> entityList = imexRepository.findAllByCountryOrderBySeq6Desc(country);
		log.info("string {}", entityList);
		List<BycounImExPriceDTO> dtoList = new ArrayList<>();
		
		for(BycounImExPriceEntity temp : entityList) {
			dtoList.add(BycounImExPriceDTO.toDTO(temp));}
		return dtoList;
	}

}
