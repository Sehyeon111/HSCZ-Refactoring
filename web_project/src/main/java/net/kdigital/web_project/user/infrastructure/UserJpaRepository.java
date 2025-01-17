package net.kdigital.web_project.user.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserJpaRepository extends JpaRepository<UserEntity, String> {

	Optional<UserEntity> findByUserId(String id);

	@Query("SELECT c FROM CustomerEntity c WHERE c.userRole = 'ROLE_CCA' ORDER BY c.likeTotal DESC")
	Page<UserEntity> findAllUserCCA(Pageable pageable);

	@Query(value = "SELECT * FROM (SELECT * FROM customer ORDER BY like_total DESC) WHERE ROWNUM <= 3", nativeQuery = true)
	List<UserEntity> findTop3ByLikeTotal();

	@Query(value = "SELECT cca_num, user_name, phone, company_name, company_region FROM (SELECT * FROM CUSTOMER WHERE USER_ROLE = 'ROLE_CCA') WHERE (company_region LIKE %:searchItem%) AND (user_name LIKE %:searchWord% OR company_name LIKE %:searchWord% OR company_region LIKE %:searchWord%)", nativeQuery = true)
	Page<UserEntity> findAllCCAByRegion(String searchItem, String searchWord, PageRequest of);

}
