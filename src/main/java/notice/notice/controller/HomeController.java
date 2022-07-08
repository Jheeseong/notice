package notice.notice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import notice.notice.config.auth.LoginUser;
import notice.notice.config.auth.SessionUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/")
    public String home(Model model, @LoginUser SessionUser user) {


        if (user == null) {
            return "home";
        }

        model.addAttribute("userName", user.getName());
        model.addAttribute("userImg", user.getPicture());

        return "loginHome";
    }
}
