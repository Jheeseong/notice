package notice.notice.controller;

import lombok.RequiredArgsConstructor;
import notice.notice.Dto.BoardDto;
import notice.notice.config.auth.LoginUser;
import notice.notice.config.auth.SessionUser;
import notice.notice.domain.Role;
import notice.notice.domain.User;
import notice.notice.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping({"", "/list"})
    public String list(Model model, @RequestParam(value="page", defaultValue = "1") Integer pageNum) {
        List<BoardDto> boardList = boardService.getBoardList(pageNum);
        Integer[] pageList = boardService.getPageList(pageNum);

        model.addAttribute("boardList", boardList);
        model.addAttribute("pageList", pageList);

        return "board/list";
    }

    // 글쓰는 페이지

    @GetMapping("/post")
    public String write() {
        return "board/write";
    }

    // 글을 쓴 뒤 POST 메서드로 글 쓴 내용을 DB에 저장
    // 그 후에는 /list 경로로 리디렉션해준다.
    @PostMapping("/post")
    public String write(@ModelAttribute BoardDto boardDto, @LoginUser SessionUser sessionUser) {

        boardService.savePost(boardDto, sessionUser.getEmail());
        return "redirect:/board/list";
    }

    // 게시물 상세 페이지이며, {no}로 페이지 넘버를 받는다.
    // PathVariable 애노테이션을 통해 no를 받음

    @GetMapping("/post/{no}")
    public String detail(@PathVariable("no") Long no, Model model) {
        BoardDto boardDTO = boardService.getPost(no);

        model.addAttribute("boardDto", boardDTO);
        return "board/detail";
    }

    // 게시물 수정 페이지이며, {no}로 페이지 넘버를 받는다.

    @GetMapping("/post/edit/{no}")
    public String edit(@PathVariable("no") Long no, Model model) {
        BoardDto boardDTO = boardService.getPost(no);

        model.addAttribute("boardDto", boardDTO);
        return "board/update";
    }

    // 위는 GET 메서드이며, PUT 메서드를 이용해 게시물 수정한 부분에 대해 적용

    @PutMapping("/post/edit/{no}")
    public String update(BoardDto boardDTO, @LoginUser SessionUser sessionUser) {
        boardService.savePost(boardDTO, sessionUser.getEmail());

        return "redirect:/board/list";
    }

    // 게시물 삭제는 deletePost 메서드를 사용하여 간단하게 삭제할 수 있다.

    @DeleteMapping("/post/{no}")
    public String delete(@PathVariable("no") Long no) {
        boardService.deletePost(no);

        return "redirect:/board/list";
    }

    // 검색
    // keyword를 view로부터 전달 받고
    // Service로부터 받은 boardDtoList를 model의 attribute로 전달해준다.

    @GetMapping("/search")
    public String search(@RequestParam(value="keyword") String keyword, Model model) {
        List<BoardDto> boardDtoList = boardService.searchPosts(keyword);

        model.addAttribute("boardList", boardDtoList);

        return "board/list";
    }

}
