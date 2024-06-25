package net.kdigital.web_project.dto;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.ToString;
import net.kdigital.web_project.user.domain.User;

@ToString
public class LoginUserDetails implements UserDetails {

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
	private static final long serialVersionUID = 1L;

	public LoginUserDetails(User customerDTO) {
		super();
		this.userId = customerDTO.getUserId();
		this.userPwd = customerDTO.getUserPwd();
		this.userName = customerDTO.getUserName();
		this.userRole = customerDTO.getUserRole();
		this.phone = customerDTO.getPhone();
		this.email = customerDTO.getEmail();
		this.companyName = customerDTO.getCompanyName();
		this.companyRegion = customerDTO.getCompanyRegion();
		this.likeTotal = customerDTO.getLikeTotal();
		this.ccaNum = customerDTO.getCcaNum();
		this.selfInfo = customerDTO.getSelfInfo();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collection = new ArrayList<>();
		collection.add(new GrantedAuthority() {
			private static final long serialVersionUID = 1L;

			@Override
			public String getAuthority() {
				return userRole;
			}
		});
		return collection;
	}

	@Override
	public String getPassword() {
		return this.userPwd;
	}

	@Override
	public String getUsername() {
		return this.userId;
	}

	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {

		return true;
	}

	// 유저의 아이디가 아닌 이름을 가져오는 메소드 추가
	public String getUserFullName() {
		return this.userName;
	}

}
