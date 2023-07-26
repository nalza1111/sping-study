package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.domain.MemberForm;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

//spring container가 Controller붙은 클래스 객체를 생성해서 넣어둠(스프링빈)
@Controller
public class MemberController {

    //MemberService는 스프링 컨테이너가 관리중이기에 스프링 컨테이너를 거쳐야 함
    //private final MemberService memberService = new MemberService();

    private final MemberService memberService;

    //MemberService에 @Service
    //MemoryMemberRepository @Repository 없으면 에러남
    //스프링 컨테이너에 등록이 안되어 있어 주입이 안됨
    //Dependency Ijection (DI, 의존성 주입)
    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String creatForm() {
        return "members/createMemberForm";
    }

    @PostMapping("members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
