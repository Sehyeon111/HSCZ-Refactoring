package net.kdigital.web_project.userItem.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.kdigital.web_project.common.domain.exception.ResouceNotFoundException;
import net.kdigital.web_project.mock.FakeBCryptEncoder;
import net.kdigital.web_project.mock.FakeUserItemRepository;
import net.kdigital.web_project.mock.FakeUserRepository;
import net.kdigital.web_project.user.domain.User;
import net.kdigital.web_project.userItem.domain.UserItem;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class UserItemServiceTest {
    public UserItemService userItemService;

    @BeforeEach
    void init() {
        FakeUserRepository fakeUserRepository = new FakeUserRepository();
        FakeUserItemRepository fakeUserItemRepository = new FakeUserItemRepository();

        userItemService = UserItemService.builder()
                .userItemRepository(fakeUserItemRepository)
                .userRepository(fakeUserRepository)
                .build();

        fakeUserRepository.save(User.builder()
                .userId("duri1549")
                .userPwd("asdfghjk")
                .userName("duri1549")
                .userRole("ROLE_CCA")
                .phone("010-5842-8584")
                .email("duri1549@gmail.com")
                .companyName("현대")
                .companyRegion("성남")
                .likeTotal(0)
                .ccaNum(14)
                .selfInfo("현대 최고!")
                .build());
    }

    @Test
    public void userItem을_repository에_저장할_수_있다() {
        // given
        UserItem userItem = UserItem.builder()
                .userId("duri1549")
                .firstItem("반도체")
                .secondItem("전자회로")
                .thirdItem("철강")
                .build();
        // when
        userItemService.insertItem(userItem);
        UserItem result = userItemService.findItem("duri1549");
        // then
        assertThat(result.getItemId()).isEqualTo(1);
        assertThat(result.getUserId()).isEqualTo("duri1549");
        assertThat(result.getFirstItem()).isEqualTo("반도체");
        assertThat(result.getSecondItem()).isEqualTo("전자회로");
        assertThat(result.getThirdItem()).isEqualTo("철강");
    }

    @Test
    public void 유효하지_않은_userId면_insertItem에러를_던진다() {
        // given
        UserItem userItem = UserItem.builder()
                .userId("duri1544")
                .firstItem("반도체")
                .secondItem("전자회로")
                .thirdItem("철강")
                .build();
        // when
        // then
        assertThatThrownBy(() -> {
            userItemService.insertItem(userItem);
        }).isInstanceOf(ResouceNotFoundException.class);

    }

    @Test
    public void userId로_User을_찾을_수_있다() {
        // given
        // when
        User result = userItemService.findByUserId("duri1549");
        // then
        assertThat(result.getUserId()).isEqualTo("duri1549");
    }

    @Test
    public void userId로_해당하는_userItem을_찾을_수_있다() {
        // given
        userItemService.insertItem(UserItem.builder()
                .userId("duri1549")
                .firstItem("반도체")
                .secondItem("전자회로")
                .thirdItem("철강")
                .build());
        // when
        UserItem result = userItemService.findItem("duri1549");
        // then
        assertThat(result).isNotNull();
        assertThat(result.getUserId()).isEqualTo("duri1549");
        assertThat(result.getFirstItem()).isEqualTo("반도체");
        assertThat(result.getSecondItem()).isEqualTo("전자회로");
        assertThat(result.getThirdItem()).isEqualTo("철강");

    }

    @Test
    public void userItem을_업데이트_할_수_있다() {
        // given
        userItemService.insertItem(UserItem.builder()
                .userId("duri1549")
                .firstItem("반도체")
                .secondItem("전자회로")
                .thirdItem("철강")
                .build());
        UserItem input = UserItem.builder()
                .firstItem("화학유기물")
                .secondItem("석유")
                .thirdItem("기계류")
                .build();
        // when

        UserItem result = userItemService.updateItem("duri1549", input);
        // then
        assertThat(result).isNotNull();
        assertThat(result.getUserId()).isEqualTo("duri1549");
        assertThat(result.getFirstItem()).isEqualTo("화학유기물");
        assertThat(result.getSecondItem()).isEqualTo("석유");
        assertThat(result.getThirdItem()).isEqualTo("기계류");

    }

}
