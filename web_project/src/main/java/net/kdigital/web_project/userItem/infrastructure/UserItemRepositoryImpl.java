package net.kdigital.web_project.userItem.infrastructure;

import java.util.Optional;

import lombok.RequiredArgsConstructor;
import net.kdigital.web_project.user.domain.User;
import net.kdigital.web_project.user.infrastructure.UserEntity;
import net.kdigital.web_project.userItem.domain.UserItem;
import net.kdigital.web_project.userItem.service.port.UserItemRepository;

@RequiredArgsConstructor
public class UserItemRepositoryImpl implements UserItemRepository {

    private final UserItemJpaRepository userItemJpaRepository;

    @Override
    public void save(UserItem userItem, User user) {
        userItemJpaRepository.save(UserItemEntity.from(userItem, UserEntity.from(user)));
    }

    @Override
    public Optional<UserItem> findByUser(User user) {
        return userItemJpaRepository.findByUser(UserEntity.from(user))
                .map(entity -> entity.toModel());
    }

}
