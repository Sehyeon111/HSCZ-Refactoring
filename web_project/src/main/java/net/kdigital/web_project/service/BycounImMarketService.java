package net.kdigital.web_project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kdigital.web_project.dto.BycounImMarketDTO;
import net.kdigital.web_project.entity.BycounImMarketEntity;
import net.kdigital.web_project.repository.BycounImMarketRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class BycounImMarketService {
	public final BycounImMarketRepository repository;

	public List<BycounImMarketDTO> bycounImMarketRanking(String country) {
		log.info("======== (서비스) piechart나라 : {}", country);
		List<BycounImMarketEntity> entityList = repository.findAllByCountryOrderBySeq5Desc(country);
		log.info("========= (서비스) piechart : {}", entityList);
		List<BycounImMarketDTO> dtoList = new ArrayList<>();
		// for(BycounImMarketEntity temp : entityList) {
		// if(temp.getDateYear()==2019) {
		// dtoList.add(BycounImMarketDTO.toDTO(temp));
		// }
		// }
		entityList.forEach((entity) -> dtoList.add(BycounImMarketDTO.toDTO(entity)));
		return dtoList;
	}
}
