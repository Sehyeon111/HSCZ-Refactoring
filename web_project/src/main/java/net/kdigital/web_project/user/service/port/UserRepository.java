package net.kdigital.web_project.user.service.port;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import net.kdigital.web_project.user.domain.User;
import net.kdigital.web_project.user.infrastructure.UserEntity;

public interface UserRepository {

    // boolean existsById(String userId);

    Optional<User> findById(String replyWriter);

    void save(User user);

    Page<User> findAllUserCCA(Pageable pageable);

    // Optional<CustomerEntity> findByUserId(String username);

    // List<UserEntity> findTop3ByLikeTotal();

}
