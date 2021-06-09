package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    /**
     * static 의 index.html 웰컴페이지를 무시 → / 로 매핑된게 있으므로
     */
    @GetMapping("/")
    public String home(){

        return "home";
    }

}
