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

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members/test")
    public String memberSampleData() {
        Member member1 = new Member();
        member1.setName("신민석");
        member1.setEmail("sms@user.com");
        member1.setCar_number("11가1111");
        member1.setRole(1);

        memberService.join(member1);

        Member member2 = new Member();
        member2.setName("김영솔");
        member2.setEmail("kys@user.com");
        member2.setCar_number("12가1234");
        member2.setRole(1);

        memberService.join(member2);

        Member member3 = new Member();
        member3.setName("조영주");
        member3.setEmail("jyj@user.com");
        member3.setCar_number("221가2222");
        member3.setRole(1);

        memberService.join(member3);

        Member member4 = new Member();
        member4.setName("김찬혁");
        member4.setEmail("kch@user.com");
        member4.setCar_number("456나1231");
        member4.setRole(1);

        memberService.join(member4);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
