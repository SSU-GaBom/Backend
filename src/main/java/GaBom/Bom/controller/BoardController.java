package GaBom.Bom.controller;

import GaBom.Bom.model.Board;
import GaBom.Bom.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardRepository boardRepository;


    @GetMapping("/list")
    public List<Board> BoardList(){
        List<Board> boards = boardRepository.findAll();
        return boards;
    }


    @PostMapping("/list")
    public List<Board> PostBoard(@RequestBody Board board){
        boardRepository.save(board);
        return boardRepository.findAll();
    }


    @GetMapping("/list/{board_id}")
    public Board one(@PathVariable Long board_id) {
        return boardRepository.findById(board_id).orElse(null);
    }



    //인증정보를 자기가 가져와야함
//    public String postForm(@Valid Board board, BindingResult bindingresult, Authentication authentication) { //사이즈같은 조건에
//        // 맞지 않는다면
//        boardValidator.validate(board,bindingresult);
//        if (bindingresult.hasErrors()) {
//            return "Error Happened! in BindingResult";
//        }
//        String username = authentication.getName();
//        boardService.save(username,board);
//        return "redirect:/board/list";//값을뿌려야함
//    }

//    @GetMapping("/list/{id}")
//    public String findMember(@PathVariable("id") Long id){
//        Board board = boardRepository.findById(id).get();
//        return board.getTitle();
//    }
//
//
//    @PostConstruct // 스프링 어플리케이션이 올라올떄 설정
//    public void init(){
//        boardRepository.save(new Board(1L,"title","내용",null,null,30000L));
//    }

}