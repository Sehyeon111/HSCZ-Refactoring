package net.kdigital.web_project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kdigital.web_project.dto.BycounIeProductDTO;
import net.kdigital.web_project.entity.BycounIeProductEntity;
import net.kdigital.web_project.repository.BycounIeProductRepository;


@Service
@Slf4j
@RequiredArgsConstructor
public class BycounIeProductService {
	private final BycounIeProductRepository bycounIeProductRepository;

	public List<BycounIeProductDTO> selectAll(String country) {
		
		List<BycounIeProductEntity> entityList = bycounIeProductRepository.findAll(Sort.by(Sort.Direction.ASC, "seq8"));
		List<BycounIeProductDTO> dtoList = new ArrayList<>();	
		log.info("왜 소수점 안나옴?{}", entityList);
		//entity를 dto로 변환하여 List에 담는 작업
		entityList.forEach((entity) -> dtoList.add(BycounIeProductDTO.toDTO(entity)));
		return dtoList;
	}//end selectAll
	
	
}//end  class
