package notice.notice.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import notice.notice.Dto.CommentDto;
import notice.notice.config.auth.LoginUser;
import notice.notice.config.auth.SessionUser;
import notice.notice.service.CommentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/board/post/{no}/comment")
    public String createComment(@PathVariable("no") Long boardId,
                                @RequestBody CommentDto commentDto,
                                @LoginUser SessionUser user) {
        commentService.createComment(boardId, commentDto, user.getEmail());
        log.info(commentDto.getContents());

        return "redirect:/";
    }

    @DeleteMapping("/board/post/{no}/comment/{commentId}")
    public String delete(@PathVariable Long commentId) {
        commentService.commentDelete(commentId);

        return "redirect:/";
    }
}
