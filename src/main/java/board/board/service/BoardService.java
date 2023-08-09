package board.board.service;

import board.board.domain.Board;
import board.board.repository.BoardRepository;
import board.board.repository.MemoryBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    // 글 다 보여주기
    public List<Board> ShowAll(){

        return boardRepository.findAll();
    }

    // 글쓰기
    public void createWriting(Board board){

        boardRepository.save(board);
    }

    // 글 확인
    public Optional<Board> ShowOne(String id){

        return boardRepository.findById(id);
    }

}
