package com.smartf.comu.controller;

import com.smartf.comu.domain.FillLog;
import com.smartf.comu.domain.Reservoir;
import com.smartf.comu.dto.FillLogDto;
import com.smartf.comu.dto.LogReportDto;
import com.smartf.comu.dto.form.FillLogAddForm;
import com.smartf.comu.service.FillLogService;
import com.smartf.comu.service.ReservoirService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class FillLogController {

    private final FillLogService fillLogService;
    private final ReservoirService reservoirService;

    @ResponseBody
    @GetMapping("/logReports/carNumber/{carNumber}")
    public List<LogReportDto> getCarMonthlyReport(@PathVariable String carNumber) {
        List<LogReportDto> logReportDto = fillLogService.getMyGraphDataByCarNumber(carNumber);
        return logReportDto;
    }

    // 주입 로그 추가
    @PostMapping("/fillLog")
    @PreAuthorize("hasRole('BRANCH')")
    public String addFillLog(FillLogAddForm form) {

        if (!reservoirService.isMyReservoir(form.getReservoirId())) {
            return "잘못된 접근입니다";
        }

        log.info("권한 검사 통과");
        FillLogDto newFillLogDto = FillLogDto.builder()
                .product("임의조정")
                .amount(form.getAmount())
                .datetime(form.getDatetime())
                .reservoirId(form.getReservoirId())
                .build();


        fillLogService.addLog(newFillLogDto);

        return "redirect:/stock";
    }


    // 미완성---
    @GetMapping("/dashboard/{branchId}")
    public String dashboardById(@PathVariable Long id, Model model) {
        List<FillLog> fillLogs = fillLogService.getLogsByBranchId(id);
        List<Reservoir> reservoirs = reservoirService.getMyBranchReservoirs();
        model.addAttribute("fillLogs", fillLogs);
        model.addAttribute("reservoirs", reservoirs);
        return "dashboard/register_place";
    }

}
