package notice.notice.controller;


import com.nimbusds.oauth2.sdk.SuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import notice.notice.Dto.CommentDto;
import notice.notice.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("comment")
@Slf4j
public class CommentController {

    private final CommentService commentService;
    @PostMapping("/post/{no}/comment")
    public String createComment(@PathVariable(name = "no") Long boardId,
                                                 @Valid CommentDto commentDto,
                                                 Principal principal) {
        commentService.createComment(boardId, commentDto, principal.getName());

        return "board/list";
    }
}
