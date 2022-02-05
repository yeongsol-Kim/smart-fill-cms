package hello.hellospring.controller;

import hello.hellospring.domain.FillLog;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DashboardController {


    @GetMapping("/")
    public String dashboard(Model model) {
        return "dashboard/register_place";
    }
}
