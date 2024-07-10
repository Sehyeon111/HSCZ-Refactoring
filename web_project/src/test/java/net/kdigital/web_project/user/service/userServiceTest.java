package net.kdigital.web_project.user.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.kdigital.web_project.common.domain.exception.ResouceNotFoundException;
import net.kdigital.web_project.mock.FakeBCryptEncoder;
import net.kdigital.web_project.mock.FakeUserRepository;
import net.kdigital.web_project.user.UserService;
import net.kdigital.web_project.user.domain.User;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

public class UserServiceTest {
    private UserService userService;

    @BeforeEach
    void init() {
        // FakeBCryptEncoder fakeBCryptEncoder = new FakeBCryptEncoder();
        FakeUserRepository fakeUserRepository = new FakeUserRepository();
        userService = UserService.builder()
                .bCryptEncoder(new FakeBCryptEncoder("asdfghjk"))
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
    public void User_객체를_이용하여_회원가입을_할_수_있다() {

        // given
        User user = User.builder()
                .userId("ssehn9327")
                .userPwd("flysh1026!")
                .userName("ssehn9327")
                .userRole("ROLE_CCA")
                .phone("010-5842-8584")
                .email("dyri1549@gmail.com")
                .companyName("삼성 SDI")
                .companyRegion("판교")
                .likeTotal(1)
                .ccaNum(1234)
                .selfInfo("안녕하세요")
                .build();
        // when
        boolean result = userService.joinProc(user);
        // then

        assertThat(result);
    }

    @Test
    public void 이미_ID가_존재한다면_회원가입을_할_수_없다() {

        // given
        User user = User.builder()
                .userId("duri1549")
                .userPwd("flysh1026!")
                .userName("ssehn9327")
                .userRole("ROLE_CCA")
                .phone("010-5842-8584")
                .email("dyri1549@gmail.com")
                .companyName("삼성 SDI")
                .companyRegion("판교")
                .likeTotal(1)
                .ccaNum(1234)
                .selfInfo("안녕하세요")
                .build();
        // when
        boolean result = userService.joinProc(user);
        // then

        assertThat(!result);
    }

    @Test
    public void UserId를_이용하여_유저를_찾을_수_있다() {

        // given
        // when
        User result = userService.findById("duri1549");
        // then
        assertAll(
                () -> assertThat(result).isNotNull(), () -> assertThat(result.getUserId()).isEqualTo("duri1549"),
                () -> assertThat(result.getUserPwd()).isEqualTo("asdfghjk"),
                () -> assertThat(result.getUserRole()).isEqualTo("ROLE_CCA"),
                () -> assertThat(result.getPhone()).isEqualTo("010-5842-8584"),
                () -> assertThat(result.getEmail()).isEqualTo("duri1549@gmail.com"),
                () -> assertThat(result.getCompanyName()).isEqualTo("현대"),
                () -> assertThat(result.getCompanyRegion()).isEqualTo("성남"),
                () -> assertThat(result.getLikeTotal()).isEqualTo(0),
                () -> assertThat(result.getCcaNum()).isEqualTo(14),
                () -> assertThat(result.getSelfInfo()).isEqualTo("현대 최고!"));

    }

    @Test
    public void 존재하지_않는_Id면_findById_에러를_던진다() {

        // given
        // when
        // then
        assertThatThrownBy(() -> {
            userService.findById("duri1544");
        }).isInstanceOf(ResouceNotFoundException.class);
    }

    @Test
    public void 회원가입을_하면_Pwd가_Encoding_된다() {

        // given
        User user = User.builder()
                .userId("ssehn9327")
                .userPwd("flysh1026!")
                .userName("ssehn9327")
                .userRole("ROLE_CCA")
                .phone("010-5842-8584")
                .email("dyri1549@gmail.com")
                .companyName("삼성 SDI")
                .companyRegion("판교")
                .likeTotal(1)
                .ccaNum(1234)
                .selfInfo("안녕하세요")
                .build();
        // when
        userService.joinProc(user);
        User result = userService.findById("ssehn9327");
        // then
        assertThat(result.getUserId()).isEqualTo("ssehn9327");
        assertThat(result.getUserPwd()).isEqualTo("asdfghjk");

    }

    @Test
    public void updateUser를_통해_update할_수_있다() {

        // given
        User updateUser = User.builder()
                .userId("duri1549")
                .userPwd("asdfghjk!")
                .userName("duri1549")
                .userRole("ROLE-_CCA")
                .phone("010-5842-8584")
                .email("dyri1549@gmail.com")
                .companyName("SK하이닉스")
                .companyRegion("청주")
                .likeTotal(1)
                .ccaNum(1234)
                .selfInfo("SK 하이닉스로 오세요")
                .build();
        // when
        userService.updateUser("duri1549", updateUser);
        User result = userService.findById("duri1549");
        // then
        assertThat(result.getUserId()).isEqualTo("duri1549");
        assertThat(result.getCompanyName()).isEqualTo("SK하이닉스");
        assertThat(result.getCompanyRegion()).isEqualTo("청주");
        assertThat(result.getSelfInfo()).isEqualTo("SK 하이닉스로 오세요");

    }

    @Test
    public void 추천으로_좋아요_수를_증가시킬_수_있다() {
        // given

        // when
        User result = userService.increaseTotalLike("duri1549");
        // then
        assertEquals(1, result.getLikeTotal());
    }

    @Test
    public void 상위_3명의_관세사를_가져올_수_있다() {
        // given
        userService.joinProc(User.builder()
                .userId("ssehn9324")
                .userPwd("eeee")
                .userName("ssehn9324")
                .userRole("ROLE_CCA")
                .phone("010-5842-8584")
                .email("duri1549@gmail.com")
                .companyName("현대")
                .companyRegion("성남")
                .likeTotal(4)
                .ccaNum(14)
                .selfInfo("현대 최고!")
                .build());
        userService.joinProc(User.builder()
                .userId("ssehn9328")
                .userPwd("eeee")
                .userName("ssehn9328")
                .userRole("ROLE_CCA")
                .phone("010-5842-8584")
                .email("duri1549@gmail.com")
                .companyName("현대")
                .companyRegion("성남")
                .likeTotal(10)
                .ccaNum(14)
                .selfInfo("현대 최고!")
                .build());
        // when
        List<User> result = userService.selectTop3CCA();
        // then
        assertThat(result.size()).isEqualTo(3);
        assertThat(result.get(0).getUserId()).isEqualTo("ssehn9328");
        assertThat(result.get(1).getUserId()).isEqualTo("ssehn9324");
        assertThat(result.get(2).getUserId()).isEqualTo("duri1549");
    }
}
