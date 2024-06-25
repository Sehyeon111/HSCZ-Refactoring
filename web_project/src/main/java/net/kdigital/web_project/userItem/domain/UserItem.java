package net.kdigital.web_project.userItem.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserItem {
	private Long itemId;
	private String userId;
	private String firstItem;
	private String secondItem;
	private String thirdItem;

	public UserItem update(UserItem originalUserItem, UserItem userItem) {
		return UserItem.builder()
				.itemId(originalUserItem.getItemId())
				.userId(originalUserItem.getUserId())
				.firstItem(userItem.getFirstItem())
				.secondItem(userItem.getSecondItem())
				.thirdItem(userItem.getThirdItem())
				.build();
	}

}
