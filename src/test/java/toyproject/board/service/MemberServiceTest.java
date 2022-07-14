package toyproject.board.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import toyproject.board.domain.Member;
import toyproject.board.dto.MemberDto;
import toyproject.board.repository.MemberRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void joinMember() {
        MemberDto memberDto = new MemberDto("testId", "testPw", "tester", "0101234568", "testNickname");

        Long memberId = memberService.joinMember(memberDto);

        Optional<Member> findMember = memberRepository.findById(memberId);
        assertThat(findMember).isPresent();
    }

    @Test
    void withdraw() {
        Member newMember = new Member("testId", "testPw", "tester", "0101234568", "testNickname");
        Member savedMember = memberRepository.save(newMember);

        memberService.withdraw(savedMember.getId());

        Optional<Member> findMember = memberRepository.findById(savedMember.getId());
        assertThat(findMember).isEmpty();
    }
}