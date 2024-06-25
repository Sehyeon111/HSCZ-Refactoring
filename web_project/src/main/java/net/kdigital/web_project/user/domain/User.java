package net.kdigital.web_project.user.domain;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.kdigital.web_project.common.infrastructure.BCryptEncoder;
import net.kdigital.web_project.user.infrastructure.UserEntity;

@Getter
public class User {

	private String userId;
	private String userPwd;
	private String userName;
	private String userRole;
	private String phone;
	private String email;
	private String companyName;
	private String companyRegion;
	private int likeTotal;
	private int ccaNum;
	private String selfInfo;

	@Builder
	public User(String userId, String userPwd, String userName, String userRole, String phone, String email,
			String companyName, String companyRegion, int likeTotal, int ccaNum, String selfInfo) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.userRole = userRole;
		this.phone = phone;
		this.email = email;
		this.companyName = companyName;
		this.companyRegion = companyRegion;
		this.likeTotal = likeTotal;
		this.ccaNum = ccaNum;
		this.selfInfo = selfInfo;
	}

	public User passWordEncoding(BCryptEncoder BCryptEncoder) {
		this.userPwd = BCryptEncoder.encoding(userPwd);
		return this;
	}

	public User increaseTotalLike() {
		this.likeTotal = this.likeTotal + 1; // likeTotal 증가
		return this;
	}

	public User update(User updateUser) {
		// this.userId = updateUser.getUserId();
		this.userName = updateUser.getUserName();
		// this.userRole = userRole;
		this.phone = updateUser.getPhone();
		this.email = updateUser.getEmail();
		this.companyName = updateUser.getCompanyName();
		this.companyRegion = updateUser.getCompanyRegion();
		// this.likeTotal = likeTotal;
		// this.ccaNum = ccaNum;
		this.selfInfo = updateUser.getSelfInfo();
		return this;
	}
}
