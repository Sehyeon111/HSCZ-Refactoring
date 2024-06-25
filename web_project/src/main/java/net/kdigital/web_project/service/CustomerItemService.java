package net.kdigital.web_project.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.kdigital.web_project.dto.CustomerItemDTO;
import net.kdigital.web_project.entity.CustomerItemEntity;
import net.kdigital.web_project.repository.CustomerItemRepository;
import net.kdigital.web_project.user.infrastructure.UserEntity;
import net.kdigital.web_project.user.infrastructure.UserJpaRepository;

@Service

@RequiredArgsConstructor
public class CustomerItemService {
	private final UserJpaRepository customerRepository;
	private final CustomerItemRepository customerItemRepository;

	public void insertItem(CustomerItemDTO customerItemDTO) {
		Optional<UserEntity> customerEntity = customerRepository.findById(customerItemDTO.getUserId());

		if (customerEntity.isPresent()) {
			UserEntity entity = customerEntity.get();
			CustomerItemEntity itemEntity = CustomerItemEntity.toEntity(customerItemDTO, entity);

			customerItemRepository.save(itemEntity);
		}

	}

	public CustomerItemDTO findItem(String username) {
		Optional<UserEntity> customerEntity = customerRepository.findById(username);
		CustomerItemEntity customerItemEntity = customerItemRepository.findByCustomerEntity(customerEntity.get());

		return CustomerItemDTO.toDTO(customerItemEntity, username);
	}

	@Transactional
	public CustomerItemDTO updateItem(String username, CustomerItemDTO customerItemDTO) {
		UserEntity customerEntity = customerRepository.findById(username).get();
		CustomerItemEntity entity = customerItemRepository.findByCustomerEntity(customerEntity);

		// log.info("{}", customerEntity);
		entity.setFirstItem(customerItemDTO.getFirstItem());
		entity.setSecondItem(customerItemDTO.getSecondItem());
		entity.setThirdItem(customerItemDTO.getThirdItem());

		return CustomerItemDTO.toDTO(entity, customerEntity.getUserId());
	}

}
