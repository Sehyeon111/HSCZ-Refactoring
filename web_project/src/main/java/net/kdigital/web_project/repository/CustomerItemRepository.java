package net.kdigital.web_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.kdigital.web_project.entity.CustomerItemEntity;
import net.kdigital.web_project.user.infrastructure.UserEntity;

public interface CustomerItemRepository extends JpaRepository<CustomerItemEntity, Long> {

	CustomerItemEntity findByCustomerEntity(UserEntity customerEntity);
}
