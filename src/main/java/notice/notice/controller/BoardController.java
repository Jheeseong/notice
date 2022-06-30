package notice.notice.controller;

import lombok.RequiredArgsConstructor;
import notice.notice.domain.Board;
import notice.notice.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/boards/new")
    public String createBoard(Model model) {
        model.addAttribute("boardForm", new BoardForm());
        return "boards/createBoardForm";
    }

    @PostMapping("/boards/new")
    public String create(@Valid BoardForm form, BindingResult result) {

        if (result.hasErrors()) {
            return "boards/createBoardForm";
        }

        Board board = new Board();
        board.setContent(form.getContent());
        board.setTitle(form.getTitle());

        boardService.join(board);
        return "redirect:/";
    }
}
