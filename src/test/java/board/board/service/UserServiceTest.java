package board.board.service;

import board.board.domain.User;
import board.board.repository.MemoryUserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

public class UserServiceTest {

    UserService userService;
    MemoryUserRepository userRepository;
    @BeforeEach
    public void beforeEach(){
        userRepository = new MemoryUserRepository();
        userService = new UserService(userRepository);
    }

    @AfterEach
    public void afterEach(){
        userRepository.clearStore();
    }

    @Test
    public void join(){
        User user = new User();
        user.setId("spring");
        user.setPassword("1234");

        userService.join(user);

        User result = userService.findOne(user.getId()).get();
        Assertions.assertThat(result).isEqualTo(user);
    }

    @Test
    public void DuplicateId(){
        User user1 = new User();
        user1.setId("spring");
        user1.setPassword("1234");

        User user2 = new User();
        user2.setId("spring");
        user2.setPassword("5678");

        userService.join(user1);

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> userService.join(user2));
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 아이디입니다.");
    }

    @Test
    public void CheckPwd(){
        User user = new User();
        user.setId("spring");
        user.setPassword("123");

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> userService.join(user));
        Assertions.assertThat(e.getMessage()).isEqualTo("비밀번호는 4자 이상이어야 합니다.");
    }

    // 로그인 성공
    @Test
    public void ValidLogin(){
        User user = new User();
        user.setId("spring");
        user.setPassword("1234");
        userService.join(user);

        boolean result = userService.login(user);
        Assertions.assertThat(result).isTrue();
    }

    // 로그인 실패
    @Test
    public void InValidLogin(){
        User user = new User();
        user.setId("spring");
        user.setPassword("1234");
//        userService.join(user);

        boolean result = userService.login(user);
        Assertions.assertThat(result).isFalse();

    }
}
