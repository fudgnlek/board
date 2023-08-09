package board.board.service;

import board.board.domain.Board;
import board.board.repository.MemoryBoardRepository;
import board.board.repository.MemoryUserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

public class BoardServiceTest {

    BoardService boardService;
    MemoryBoardRepository boardRepository;
    @BeforeEach
    public void beforeEach(){
        boardRepository = new MemoryBoardRepository();
        boardService = new BoardService(boardRepository);
    }

    @AfterEach
    public void afterEach(){
        boardRepository.clearStore();
    }

    @Test
    public void ShowAll(){
        Board board1 = new Board();
        board1.setId("spring1");
        board1.setTitle("Title1");
        board1.setContent("Content1");

        Board board2 = new Board();
        board2.setId("spring2");
        board2.setTitle("Title2");
        board2.setContent("Content2");

        boardService.createWriting(board1);
        boardService.createWriting(board2);

        List<Board> result = boardService.ShowAll();
        Assertions.assertThat(result.size()).isEqualTo(2);

    }

    @Test
    public void createWriting(){
        Board board1 = new Board();
        board1.setId("spring1");
        board1.setTitle("Title1");
        board1.setContent("Content1");

        boardService.createWriting(board1);

        Optional<Board> result = boardService.ShowOne(board1.getId());
        Assertions.assertThat(result.isPresent()).isTrue();

    }


    @Test
    public void ShowOne(){
        Board board1 = new Board();
        board1.setId("spring1");
        board1.setTitle("Title1");
        board1.setContent("Content1");

        Optional<Board> result = boardService.ShowOne("spring2");
        Assertions.assertThat(result.isPresent()).isFalse();
    }


}
