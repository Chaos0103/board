package toyproject.board.dto;

import lombok.Data;
import toyproject.board.domain.ReportType;

@Data
public class ReportDto {

    private Long id;
    private Long memberId;
    private Long postId;
    private ReportType type;

    public ReportDto(ReportType type) {
        this.type = type;
    }
}
