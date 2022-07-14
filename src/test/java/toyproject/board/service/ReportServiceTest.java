package toyproject.board.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import toyproject.board.domain.Member;
import toyproject.board.domain.Post;
import toyproject.board.domain.Report;
import toyproject.board.domain.ReportType;
import toyproject.board.dto.ReportDto;
import toyproject.board.repository.MemberRepository;
import toyproject.board.repository.PostRepository;
import toyproject.board.repository.ReportRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ReportServiceTest {

    @Autowired ReportService reportService;
    @Autowired ReportRepository reportRepository;
    @Autowired PostRepository postRepository;
    @Autowired MemberRepository memberRepository;

    @Test
    void reportPost() {
        Member member = getMember();
        Post post = getPost();
        ReportDto reportDto = new ReportDto(ReportType.TYPE1);
        reportDto.setMemberId(member.getId());
        reportDto.setPostId(post.getId());

        Long reportId = reportService.reportPost(reportDto);

        Optional<Report> findReport = reportRepository.findById(reportId);
        assertThat(findReport).isPresent();
    }

    private Member getMember() {
        Member newMember = new Member("testId", "testPw", "tester", "0101234568", "testNickname");
        return memberRepository.save(newMember);
    }

    private Post getPost() {
        Post post = new Post(null, null, "test post title", "test post content", true);
        return postRepository.save(post);
    }
}