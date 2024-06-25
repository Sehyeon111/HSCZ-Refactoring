package net.kdigital.web_project.userItem.service;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import net.kdigital.web_project.common.domain.exception.ResouceNotFoundException;
import net.kdigital.web_project.user.domain.User;
import net.kdigital.web_project.user.service.port.UserRepository;
import net.kdigital.web_project.userItem.domain.UserItem;
import net.kdigital.web_project.userItem.service.port.UserItemRepository;

@Service
@Builder
@RequiredArgsConstructor
public class UserItemService {
	private final UserRepository userRepository;
	private final UserItemRepository userItemRepository;

	public void insertItem(UserItem userItem) {
		User user = findByUserId(userItem.getUserId());
		userItemRepository.save(userItem, user);
	}

	public User findByUserId(String userId) {
		return userRepository.findById(userId)
				.orElseThrow(() -> new ResouceNotFoundException("USER", userId));
	}

	public UserItem findItem(String username) {
		User user = findByUserId(username);
		return userItemRepository.findByUser(user)
				.orElseThrow(() -> new ResouceNotFoundException("UserItem", username));
	}

	@Transactional
	public UserItem updateItem(String username, UserItem userItem) {
		UserItem originalUserItem = findItem(username);
		UserItem updatedUserItem = originalUserItem.update(originalUserItem, userItem);
		return updatedUserItem;
	}

}
