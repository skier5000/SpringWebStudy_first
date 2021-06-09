package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    // MemberController 말고 다른 Controller 들이 MemberService 를 가져다 쓸 수 있다
    // → 스프링이 관리를 하게 되면 다 스프링 컨테이너에 등록하고 스프링컨테이너로부터 받아서 쓰도록 바꿔야함
    //private final MemberService memberService = new MemberService();


    // MemberController가 생성이 될 때, 스프링 빈에 등록되어있는 MemberService 객체를 가져다가 넣어줌
    // Dependency Injection (DI)
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }
    

}
