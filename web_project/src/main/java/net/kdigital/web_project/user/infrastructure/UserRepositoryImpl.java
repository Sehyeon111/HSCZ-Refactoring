package net.kdigital.web_project.user.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import net.kdigital.web_project.user.domain.User;
import net.kdigital.web_project.user.service.port.UserRepository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    // @Override
    // public boolean existsById(String userId) {
    // return userJpaRepository.existsById(userId);
    // }

    @Override
    public Optional<User> findById(String replyWriter) {
        return userJpaRepository.findById(replyWriter).map(entity -> entity.toModel());
    }

    // public User getById(String replyWriter) {
    // return findById(replyWriter).orElseThrow(() -> new
    // ResouceNotFoundException("Users", replyWriter));
    // }

    @Override
    public void save(User user) {
        userJpaRepository.save(UserEntity.from(user));
    }

    @Override
    public Page<User> findAllUserCCA(Pageable pageable) {
        return userJpaRepository.findAllUserCCA(pageable)
                .map(entity -> entity.toModel());
    }

    // @Override
    // public Optional<CustomerEntity> findByUserId(String username) {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method
    // 'findByUserId'");
    // }

    @Override
    public List<User> findTop3ByLikeTotal() {
        return userJpaRepository.findTop3ByLikeTotal().stream()
                .map(entity -> entity.toModel()).collect(Collectors.toList());

    }

    @Override
    public Page<User> findAllCCAByRegion(String searchItem, String searchWord, PageRequest of) {
        return userJpaRepository.findAllCCAByRegion(searchItem, searchWord, of).map(entity -> entity.toModel());
    }

}
