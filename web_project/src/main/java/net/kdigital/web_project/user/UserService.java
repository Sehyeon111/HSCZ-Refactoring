package net.kdigital.web_project.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kdigital.web_project.common.domain.exception.ResouceNotFoundException;
import net.kdigital.web_project.common.infrastructure.BCryptEncoder;
import net.kdigital.web_project.entity.AnswerEntity;
import net.kdigital.web_project.entity.CustomerLikeEntity;
import net.kdigital.web_project.repository.AnswerRepository;
import net.kdigital.web_project.repository.CustomerLikeRepository;
import net.kdigital.web_project.user.domain.User;
import net.kdigital.web_project.user.infrastructure.UserEntity;
import net.kdigital.web_project.user.service.port.UserRepository;

@Service
@Builder
@RequiredArgsConstructor
@Slf4j
public class UserService {

	// public final CustomerLikeRepository customerLikeRepository;
	// public final AnswerRepository answerRepository;
	public final UserRepository userRepository;
	public final BCryptEncoder bCryptEncoder;

	/**
	 * 회원가입
	 * 
	 */
	public boolean joinProc(User user) {

		try {
			findById(user.getUserId());
		} catch (ResouceNotFoundException e) {
			userRepository.save(user.passWordEncoding(bCryptEncoder)); // 의존성 주입
			return true;
		}
		return false;

	}

	public User findById(String userId) {
		return userRepository.findById(userId)
				.orElseThrow(() -> new ResouceNotFoundException("Users", userId));
	}

	/**
	 * 좋아요 수 증가
	 * 
	 * @param userId
	 * @return
	 */
	@Transactional
	public User increaseTotalLike(String userId) {
		User user = findById(userId);
		user = user.increaseTotalLike(); // likeTotal 증가
		userRepository.save(user); // 데이터베이스에 변경 사항 저장
		return user;
	}

	/**
	 * 모든 관세사 페이지 객체 가져오기
	 * 
	 * @param pageable
	 * @return
	 */
	// public Page<User> findAllUserCCA(Pageable pageable) {
	// Page<User> users = userRepository.findAllUserCCA(pageable);
	// return users;

	// }

	/**
	 * 회원 정보 업데이트
	 */
	@Transactional
	public User updateUser(String username, User updateUser) {
		User originalUser = findById(username); // 회원 정보가 없을 때 에러를 던지는지 확인!

		User updatedUser = originalUser.update(updateUser);
		userRepository.save(updatedUser);
		return updatedUser;
	}

	// CCA는 CCA끼리 나중에..
	// public boolean checkIsAlreadyLiked(Long replyNum, String username) {
	// AnswerEntity answerEntity = answerRepository.findById(replyNum).get();

	// boolean isLIkeExist =
	// customerLikeRepository.existsByUserIdAndAnswerEntity(username, answerEntity);

	// return !isLIkeExist;
	// }

	// CCA는 CCA끼리 나중에..
	// public void insertCustomerLike(Long replyNum, String username) {
	// CustomerLikeEntity entity = new CustomerLikeEntity();
	// AnswerEntity answerEntity = answerRepository.findById(replyNum).get();

	// entity.setUserId(username);
	// entity.setAnswerEntity(answerEntity);

	// customerLikeRepository.save(entity);
	// }

	public List<User> selectTop3CCA() {
		List<User> ccaList = userRepository.findTop3ByLikeTotal();

		return ccaList;
	}

}
