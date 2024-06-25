package net.kdigital.web_project.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.kdigital.web_project.dto.HeadingDTO;
import net.kdigital.web_project.entity.HeadingEntity;
import net.kdigital.web_project.repository.HeadingRepository;

@Service

@RequiredArgsConstructor
public class HeadingService {

	private final HeadingRepository headingRepository;

	public HeadingDTO selectOne(String hs4digit) {

		Optional<HeadingEntity> entity = headingRepository.findById(hs4digit);

		if (entity.isPresent()) {
			HeadingEntity headingEntity = entity.get();
			HeadingDTO dto = HeadingDTO.toDTO(headingEntity);
			return dto;
		}
		return null;
	}

}
