package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage() {
        return "loginPage";
    }

    @PostMapping("/login")
    public String login(LoginForm loginForm) {
        System.out.println(loginForm.getId());
        System.out.println(loginForm.getPassword());
        if( loginForm.getId() == "user" && loginForm.getPassword() == "pass") {
            return "redirect:/admin";
        } else {
            return "loginPage";
        }
    }
}
