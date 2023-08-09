package board.board.repository;

import board.board.domain.Board;
import board.board.domain.User;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {

    void save(Board board);
    Optional<Board> findById(String id);
    List<Board> findAll();
}
