package toyproject.board.dto;

import lombok.Data;
import toyproject.board.domain.Report;
import toyproject.board.domain.ReportType;

import java.time.LocalDateTime;

@Data
public class ReportDto {

    private Long id;
    private Long memberId;
    private Long postId;
    private ReportType type;
    private LocalDateTime createdDate;

    private MemberDto memberDto;

    public ReportDto(ReportType type) {
        this.type = type;
    }

    public ReportDto(Report report) {
        this.id = report.getId();
        this.postId = report.getPost().getId();
        this.type = report.getType();
        this.createdDate = report.getCreatedDate();
        this.memberDto = new MemberDto(report.getMember());
    }
}
