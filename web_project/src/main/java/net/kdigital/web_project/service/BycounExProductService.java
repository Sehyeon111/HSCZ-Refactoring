package net.kdigital.web_project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kdigital.web_project.dto.BycounExProductDTO;

import net.kdigital.web_project.entity.BycounExProductEntity;

import net.kdigital.web_project.repository.BycounExProductRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class BycounExProductService {
	private final BycounExProductRepository exproRepository;

	public List<BycounExProductDTO> selectAll(String country) {
		log.info("Exbarchart 서비스로 옴");
		List<BycounExProductEntity> entityList = exproRepository.findAll(Sort.by(Sort.Direction.ASC, "seq4"));
		List<BycounExProductDTO> dtoList = new ArrayList<>();
		log.info("Exbarchart의 entity : {}", entityList);
		// entity를 dto로 변환하여 List에 담는 작업
		entityList.forEach((entity) -> dtoList.add(BycounExProductDTO.toDTO(entity)));
		return dtoList;
	}
}// end class
