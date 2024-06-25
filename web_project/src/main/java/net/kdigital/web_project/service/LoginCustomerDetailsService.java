package net.kdigital.web_project.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.kdigital.web_project.dto.LoginUserDetails;
import net.kdigital.web_project.user.domain.User;
import net.kdigital.web_project.user.infrastructure.UserEntity;
import net.kdigital.web_project.user.infrastructure.UserJpaRepository;

@RequiredArgsConstructor
@Service
public class LoginCustomerDetailsService implements UserDetailsService {

	private final UserJpaRepository customerRepository;

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

		UserEntity customerEntity = customerRepository.findByUserId(userId)
				.orElseThrow(() -> {
					throw new UsernameNotFoundException("err 발생");
				});
		User customerDTO = User.toDTO(customerEntity);

		return new LoginUserDetails(customerDTO);
	}

}
