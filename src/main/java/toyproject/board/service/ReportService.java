package toyproject.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.board.domain.Member;
import toyproject.board.domain.Post;
import toyproject.board.domain.Report;
import toyproject.board.dto.ReportDto;
import toyproject.board.exception.NoSuchException;
import toyproject.board.repository.MemberRepository;
import toyproject.board.repository.PostRepository;
import toyproject.board.repository.ReportRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    /**
     * 게시물 신고
     */
    public Long reportPost(ReportDto reportDto) {
        Report savedReport = reportRepository.save(getNewReport(reportDto));
        return savedReport.getId();
    }

    private Report getNewReport(ReportDto reportDto) {
        Member findMember = getMember(reportDto.getMemberId());
        Post findPost = getPost(reportDto.getPostId());
        return new Report(findMember, findPost, reportDto.getType());
    }

    private Member getMember(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> {
            throw new NoSuchException("등록되지 않은 회원입니다.");
        });
    }

    private Post getPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> {
            throw new NoSuchException("등록되지 않은 게시물입니다.");
        });

    }
}
