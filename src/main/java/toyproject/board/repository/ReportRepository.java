package toyproject.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import toyproject.board.domain.Report;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {

    @Query("select r from Report r join fetch r.member")
    List<Report> findAll();
}
