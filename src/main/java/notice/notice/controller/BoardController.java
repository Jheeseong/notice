package notice.notice.controller;

import lombok.RequiredArgsConstructor;
import notice.notice.domain.Board;
import notice.notice.domain.Member;
import notice.notice.service.BoardService;
import notice.notice.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

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
