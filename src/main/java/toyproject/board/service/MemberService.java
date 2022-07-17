package toyproject.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.board.domain.Member;
import toyproject.board.dto.MemberDto;
import toyproject.board.exception.NoSuchException;
import toyproject.board.repository.MemberRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원가입
     */
    public Long joinMember(MemberDto memberDto) {
        //아이디 중복
        //핸드폰번호 중복
        //닉네임 중복
        Member savedMember = memberRepository.save(dtoToEntity(memberDto));
        return savedMember.getId();
    }

    /**
     * 회원탈퇴
     */
    public void withdraw(Long memberId) {
        memberRepository.delete(checkMember(memberId));
    }

    /**
     * 회원조회
     */
    @Transactional(readOnly = true)
    public List<MemberDto> searchMemberList(String name) {
        return memberRepository.findByName(name).stream()
                .map(MemberDto::new)
                .toList();
    }

    private Member dtoToEntity(MemberDto memberDto) {
        return new Member(memberDto.getLoginId(), memberDto.getPassword(), memberDto.getName(), memberDto.getPhone(), memberDto.getNickname());
    }

    private Member checkMember(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> {
            throw new NoSuchException("등록되지 않은 회원입니다.");
        });
    }
}
