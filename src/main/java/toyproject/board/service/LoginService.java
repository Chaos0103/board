package toyproject.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.board.domain.Member;
import toyproject.board.dto.MemberDto;
import toyproject.board.exception.NoSuchException;
import toyproject.board.repository.MemberRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    public MemberDto login(String loginId, String password) {
        Member member = memberRepository.loginMember(loginId, password).orElseThrow(() -> {
            throw new NoSuchException("등록되지 않은 회원입니다.");
        });
        return new MemberDto(member);
    }
}
