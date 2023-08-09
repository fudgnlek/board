package board.board.repository;

import board.board.domain.Board;
import board.board.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemoryBoardRepositoryTest {

    MemoryBoardRepository repository ;

    @BeforeEach
    public void BeforeEach() {
        repository = new MemoryBoardRepository();
    }

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Board board = new Board();
        board.setId("spring");
        board.setTitle("Hello");
        board.setContent("I'm Spring");
        repository.save(board);

        Board result = repository.findById(board.getId()).get();
        Assertions.assertThat(result).isEqualTo(board);
    }

    @Test
    public void findById(){
        Board board1 = new Board();
        board1.setId("spring1");
        repository.save(board1);

        Board board2 = new Board();
        board2.setId("spring2");
        repository.save(board2);

        Board result = repository.findById("spring1").get();
        Assertions.assertThat(result).isEqualTo(board1);
    }

    @Test
    public void findAll(){
        Board board1 = new Board();
        board1.setId("spring");
        board1.setTitle("Hello");
        board1.setContent("I'm Spring");
        repository.save(board1);

        Board board2 = new Board();
        board2.setId("Java");
        board2.setTitle("Hellooo");
        board2.setContent("I'm Java");
        repository.save(board2);

        List<Board> result = repository.findAll();
        Assertions.assertThat(result.size()).isEqualTo(2);
    }


}
