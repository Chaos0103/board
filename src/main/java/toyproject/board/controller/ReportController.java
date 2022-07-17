package toyproject.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import toyproject.board.dto.ReportDto;
import toyproject.board.service.ReportService;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/report")
    public String reportList(Model model) {
        List<ReportDto> reportDtoList = reportService.reportList();
        model.addAttribute("reportDtoList", reportDtoList);
        return "/report/reportList";
    }
}
