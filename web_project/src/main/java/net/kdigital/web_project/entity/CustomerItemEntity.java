package net.kdigital.web_project.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.kdigital.web_project.dto.CustomerItemDTO;
import net.kdigital.web_project.user.infrastructure.UserEntity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
@Table(name = "customer_item")
public class CustomerItemEntity {

	@SequenceGenerator(name = "customer_item_seq", sequenceName = "customer_item_seq", initialValue = 1, allocationSize = 1)

	@Id
	@GeneratedValue(generator = "customer_item_seq")
	@Column(name = "item_id")
	private Long itemId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private UserEntity customerEntity;

	@Column(name = "first_item")
	private String firstItem;

	@Column(name = "second_item")
	private String secondItem;

	@Column(name = "third_item")
	private String thirdItem;

	public static CustomerItemEntity toEntity(CustomerItemDTO customerItemDTO, UserEntity customerEntity) {
		return CustomerItemEntity.builder()
				.itemId(customerItemDTO.getItemId())
				.customerEntity(customerEntity)
				.firstItem(customerItemDTO.getFirstItem())
				.secondItem(customerItemDTO.getSecondItem())
				.thirdItem(customerItemDTO.getThirdItem())
				.build();
	}
}
