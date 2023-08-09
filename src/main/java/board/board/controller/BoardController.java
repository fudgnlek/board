package board.board.controller;

import board.board.domain.Board;
import board.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // 이게 게시판 페이지
    @GetMapping("/board")
    public String showList(Model model){
        List<Board> board = boardService.ShowAll();
        model.addAttribute("boards",board);
//        model.addAttribute("posts.id",)
        return "boards/list";
    }

    // 글 쓰는 페이지
    @GetMapping("/write")
    public String writeForm(){
        return "boards/writeForm";
    }

    @PostMapping("/write")
    public String write(BoardForm form){
        Board board = new Board();
        board.setId(form.getId());
        board.setTitle(form.getTitle());
        board.setContent(form.getContent());
        boardService.createWriting(board);
        return "redirect:/board";
    }

    // 글 확인
    @GetMapping("/views/{id}")
    public String showOne(@PathVariable String id, Model model){
        Optional<Board> board = boardService.ShowOne(id);
        if(board.isPresent()){
            Board newBoard = board.get();
            model.addAttribute("board",newBoard);
            return "boards/show";
        } else{
            return "redirect:/board";
        }
    }








}
