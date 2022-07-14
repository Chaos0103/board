package toyproject.board.domain;

public enum ReportType {

    TYPE1("게시판 성격에 부적절함"),
    TYPE2("낚시/놀람/도배"),
    TYPE3("상업적 광고 및 판매"),
    TYPE4("욕설/비하"),
    TYPE5("유출/사칭/사기"),
    TYPE6("음란물/불건전한 만남 및 대화");

    private final String description;

    ReportType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
