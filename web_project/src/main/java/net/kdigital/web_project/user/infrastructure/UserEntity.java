package net.kdigital.web_project.user.infrastructure;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.kdigital.web_project.entity.CustomerItemEntity;
import net.kdigital.web_project.user.domain.User;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
@Entity
@Table(name = "customer")
public class UserEntity {

	@Id
	@Column(name = "user_id")
	private String userId;

	@Column(name = "user_pwd", nullable = false)
	private String userPwd;

	@Column(name = "user_name", nullable = false)
	private String userName;

	@Column(name = "user_role", nullable = false)
	private String userRole;

	@Column(name = "phone", nullable = false)
	private String phone;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "company_name")
	private String companyName;

	@Column(name = "company_region")
	private String companyRegion;

	@Column(name = "like_total")
	private int likeTotal;

	@Column(name = "cca_num", nullable = true)
	private int ccaNum;

	@Column(name = "self_info")
	private String selfInfo;

	/*
	 * item table과 관계설정
	 * 
	 */
	@OneToMany(mappedBy = "customerEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
	@OrderBy("item_id asc")
	private List<CustomerItemEntity> customerItemEntity = new ArrayList<>();

	public static UserEntity from(User user) {
		return UserEntity.builder()
				.userId(user.getUserId())
				.userPwd(user.getUserPwd())
				.userName(user.getUserName())
				.userRole(user.getUserRole())
				.phone(user.getPhone())
				.email(user.getEmail())
				.companyName(user.getCompanyName())
				.companyRegion(user.getCompanyRegion())
				.likeTotal(user.getLikeTotal())
				.ccaNum(user.getCcaNum())
				.selfInfo(user.getSelfInfo())
				.build();
	}

	public User toModel() {
		return User.builder()
				.userId(userId)
				.userPwd(userPwd)
				.userName(userName)
				.userRole(userRole)
				.phone(phone)
				.email(email)
				.companyName(companyName)
				.companyRegion(companyRegion)
				.likeTotal(likeTotal)
				.ccaNum(ccaNum)
				.selfInfo(selfInfo)
				.build();
	}
}
