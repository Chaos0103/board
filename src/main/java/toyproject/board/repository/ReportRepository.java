package toyproject.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toyproject.board.domain.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {
}
