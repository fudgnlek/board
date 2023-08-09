package board.board.repository;

import board.board.domain.Board;
import board.board.domain.User;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.*;

@Repository
public class MemoryBoardRepository implements BoardRepository {

    private final Map<String, Board> boards = new HashMap<>();

    @Override
    public void save(Board board) {
        boards.put(board.getId(),board);
    }

    @Override
    public List<Board> findAll() {
        return new ArrayList<>(boards.values());
    }

    @Override
    public Optional<Board> findById(String id) {
        return Optional.ofNullable(boards.get(id));
    }

    public void clearStore(){
        boards.clear();
    }
}

