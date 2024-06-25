package net.kdigital.web_project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kdigital.web_project.dto.BycounImProductDTO;
import net.kdigital.web_project.entity.BycounImProductEntity;
import net.kdigital.web_project.repository.BycounImproductRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class BycounImProductService {
	private final BycounImproductRepository improRepository;

	public List<BycounImProductDTO> selectAll(String country) {

		List<BycounImProductEntity> entityList = improRepository.findAll(Sort.by(Sort.Direction.ASC, "seq3"));
		List<BycounImProductDTO> dtoList = new ArrayList<>();
		log.info("inport {}", entityList);

		// entity를 dto로 변환하여 List에 담는 작업
		entityList.forEach((entity) -> dtoList.add(BycounImProductDTO.toDTO(entity)));
		return dtoList;

	}// end selectAll
}// end class
