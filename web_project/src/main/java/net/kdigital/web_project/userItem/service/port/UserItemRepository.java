package net.kdigital.web_project.userItem.service.port;

import java.util.Optional;

import net.kdigital.web_project.user.domain.User;
import net.kdigital.web_project.user.infrastructure.UserEntity;
import net.kdigital.web_project.userItem.domain.UserItem;
import net.kdigital.web_project.userItem.infrastructure.UserItemEntity;

public interface UserItemRepository {

    void save(UserItem userItem, User user);

    Optional<UserItem> findByUser(User user);

}
