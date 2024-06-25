package net.kdigital.web_project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.kdigital.web_project.dto.XyclusterDTO;
import net.kdigital.web_project.entity.XyclusterEntity;
import net.kdigital.web_project.repository.XyclusterRepository;

@Service
@RequiredArgsConstructor
public class XyclusterService {
	public final XyclusterRepository xyclusterRepository;

	/**
	 * 한국 10대 수입품목 가져옴
	 * 
	 * @return
	 */
	public List<XyclusterDTO> selectAll() {
		// log.info("서비스 도착");
		List<XyclusterEntity> entityList = xyclusterRepository.findAll(Sort.by(Sort.Direction.ASC, "seq1"));
		List<XyclusterDTO> dtoList = new ArrayList<>();

		// entity를 dto로 변환하여 List에 담는 작업
		entityList.forEach((entity) -> dtoList.add(XyclusterDTO.toDTO(entity)));
		return dtoList;
	}// end selectAll
}// end class
