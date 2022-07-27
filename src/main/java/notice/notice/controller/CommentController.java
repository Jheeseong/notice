package notice.notice.controller;


import com.nimbusds.oauth2.sdk.SuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @PostMapping("/post/{no}/comment")
    public String createComment(@PathVariable(name = "no") Long postId,
                                                 @Valid @RequestBody CommentDto commentDto,
                                                 Principal principal) {

    }
}
