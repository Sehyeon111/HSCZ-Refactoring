package net.kdigital.web_project.mock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.hibernate.mapping.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import net.kdigital.web_project.user.domain.User;
import net.kdigital.web_project.user.infrastructure.UserEntity;
import net.kdigital.web_project.user.service.port.UserRepository;

public class FakeUserRepository implements UserRepository {

    // private final AtomicLong autoGeneratedId = new AtomicLong(0);
    private List<User> list = new ArrayList<>();

    // @Override
    // public boolean existsById(String userId) {
    // Optional<User> getUser = findById(userId);
    // if (getUser.isPresent())
    // return true;
    // return false;
    // }

    @Override
    public Optional<User> findById(String replyWriter) {
        return list.stream().filter(item -> item.getUserId().equals(replyWriter)).findAny();
    }

    @Override
    public void save(User user) {
        Optional<User> getUser = findById(user.getUserId());
        if (getUser.isEmpty()) {
            list.add(user);
        } else {

            list.removeIf(item -> Objects.equals(item.getUserId(), user.getUserId()));
            list.add(user);
        }
    }

    @Override
    public Page<User> findAllUserCCA(Pageable pageable) {
        // Page<User> ccaList = new Page<User>(); // FixMe T.T
        return null;
    }

    @Override
    public List<User> findTop3ByLikeTotal() {
        List<User> ccaList = list.stream().filter(user -> user.getUserRole().equals("ROLE_CCA"))
                .collect(Collectors.toList());
        Collections.sort(ccaList, (o1, o2) -> o2.getLikeTotal() - o1.getLikeTotal());

        return ccaList.subList(0, Math.min(3, ccaList.size()));
    }

    @Override
    public Page<User> findAllCCAByRegion(String searchItem, String searchWord, PageRequest of) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllCCAByRegion'");
    }

}
