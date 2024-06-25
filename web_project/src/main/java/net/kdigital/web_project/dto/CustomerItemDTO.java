package net.kdigital.web_project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.kdigital.web_project.entity.CustomerItemEntity;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class CustomerItemDTO {
	private Long itemId;
	private String userId;
	private String firstItem;
	private String secondItem;
	private String thirdItem;
//	private CustomerDTO customerDTO;
	
	public static CustomerItemDTO toDTO(CustomerItemEntity customerItemEntity, String userId) {
		return CustomerItemDTO.builder()
				.itemId(customerItemEntity.getItemId())
				.userId(userId)
				.firstItem(customerItemEntity.getFirstItem())
				.secondItem(customerItemEntity.getSecondItem())
				.thirdItem(customerItemEntity.getThirdItem())
				.build();
	}
	
}
