package board.board.repository;

import board.board.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemoryUserRepositoryTest {

    MemoryUserRepository repository;

    @BeforeEach
    public void BeforeEach() {
        repository = new MemoryUserRepository();
    }

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        User user = new User();
        user.setId("spring");
        user.setPassword("1234");
        repository.save(user);

        User result = repository.findById(user.getId()).get();
        Assertions.assertThat(result).isEqualTo(user);
    }

    @Test
    public void findById(){
        User user1 = new User();
        user1.setId("spring1");
        repository.save(user1);

        User user2 = new User();
        user2.setId("spring2");
        repository.save(user2);

        User result = repository.findById("spring1").get();
        Assertions.assertThat(result).isEqualTo(user1);
    }
}
