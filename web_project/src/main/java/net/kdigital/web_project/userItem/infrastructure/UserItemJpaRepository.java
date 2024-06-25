package net.kdigital.web_project.userItem.infrastructure;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import net.kdigital.web_project.user.infrastructure.UserEntity;

public interface UserItemJpaRepository extends JpaRepository<UserItemEntity, Long> {

	Optional<UserItemEntity> findByUser(UserEntity userEntity);
}
