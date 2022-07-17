package toyproject.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import toyproject.board.controller.form.LoginForm;
import toyproject.board.controller.form.MemberForm;
import toyproject.board.dto.MemberDto;
import toyproject.board.dto.MemberSearch;
import toyproject.board.service.LoginService;
import toyproject.board.service.MemberService;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final LoginService loginService;

    @GetMapping("/member/new")
    public String joinMember(@ModelAttribute("memberForm") MemberForm memberForm) {
        return "/member/createMemberForm";
    }

    @PostMapping("/member/new")
    public String join(MemberForm memberForm) {
        MemberDto memberDto = getMemberDto(memberForm);
        memberService.joinMember(memberDto);
        return "redirect:/";
    }

    @GetMapping("/member")
    public String memberList(@ModelAttribute("memberSearch") MemberSearch memberSearch, Model model) {
        List<MemberDto> memberDtoList = memberService.searchMemberList(memberSearch.getName());
        model.addAttribute("memberDtoList", memberDtoList);
        return "/member/memberList";
    }

    @GetMapping("/login")
    public String loginMember(@ModelAttribute("loginForm") LoginForm loginForm) {
        return "/member/loginForm";
    }

    @PostMapping("/login")
    public String login(LoginForm loginForm, HttpSession session) {
        MemberDto loginMember = loginService.login(loginForm.getLoginId(), loginForm.getPassword());
        session.setAttribute("loginId", loginMember.getId());
        return "redirect:/";
    }

    private MemberDto getMemberDto(MemberForm memberForm) {
        return new MemberDto(memberForm.getLoginId(), memberForm.getPassword(), memberForm.getName(), memberForm.getPhone(), memberForm.getNickname());
    }
}
