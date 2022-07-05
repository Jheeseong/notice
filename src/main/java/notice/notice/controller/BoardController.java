package notice.notice.controller;

import lombok.RequiredArgsConstructor;
import notice.notice.domain.Board;
import notice.notice.domain.Member;
import notice.notice.service.BoardService;
import notice.notice.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final MemberService memberService;

    @GetMapping("/boards/{memberId}/new")
    public String createBoard(@PathVariable("memberId") Long memberId, Model model) {
        Member member = memberService.findOne(memberId);

        BoardForm form = new BoardForm();
        form.setIdName(member.getIdName());

        model.addAttribute("form", form);
        return "boards/createBoardForm";
    }

    @PostMapping("/boards/{memberId}/new")
    public String create(@PathVariable("memberId") Long memberId,
                         @Valid BoardForm form,
                         BindingResult result) {

        if (result.hasErrors()) {
            return "boards/createBoardForm";
        }

        String title = form.getTitle();
        String content = form.getContent();

        boardService.join(memberId, title, content);
        return "redirect:/boards";
    }

    @GetMapping("/boards")
    public String list(Model model) {
        List<Board> boards = boardService.findBoards();
        model.addAttribute("boardList", boards);
        return "boards/BoardList";
    }
}
