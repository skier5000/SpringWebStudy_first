package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    // MemberController 말고 다른 Controller 들이 MemberService 를 가져다 쓸 수 있다
    // → 스프링이 관리를 하게 되면 다 스프링 컨테이너에 등록하고 스프링컨테이너로부터 받아서 쓰도록 바꿔야함
    //private final MemberService memberService = new MemberService();


    // MemberController가 생성이 될 때, 스프링 빈에 등록되어있는 MemberService 객체를 가져다가 넣어줌
    // Dependency Injection (DI)
    private final MemberService memberService;

    // 생성자를 통해서 DI 주입 (가장 괜찮)
    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
        // AOP 적용 후, memberService 는 프록시로서 실제 MemberService 호출 전 인터셉트함
        // 그리고 AOP 클래스에서 joinPoint.proceed() 하면 실제 MemberService 를 호출
        System.out.println("memberService = " + memberService.getClass());
    }

    @GetMapping("/members/new")
    public String CreateForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String Create(MemberForm memberForm){
        Member member = new Member();
        member.setName(memberForm.getName());

        System.out.println("member = " + member.getName());

        memberService.join(member);

        // redirect 사용시
        return "redirect:/";
    }

    @GetMapping("/members")
    public String List(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);

        return "members/memberList";
    }

}
