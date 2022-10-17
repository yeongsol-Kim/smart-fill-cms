package com.smartf.comu.service;

import com.smartf.comu.domain.FillLog;
import com.smartf.comu.dto.FillLogDto;
import com.smartf.comu.dto.LogReportDto;
import com.smartf.comu.repository.FillLogRepository;
import com.smartf.comu.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FillLogService {

    private final FillLogRepository fillLogRepository;

    public Long join(FillLog fillLog) {
        fillLogRepository.save(fillLog);
        return fillLog.getId();
    }

    public List<FillLog> findFillLogs() {
        return fillLogRepository.findAll();
    }

    // 지점 지정 주입 내역 조회
    public List<FillLog> getLogsByBranchId(Long branchId) {
        return fillLogRepository.findByBranchId(branchId);

        // Sort
        //return fillLogRepository.findByBranchId(branchId, Sort.by(Sort.Direction.DESC, "datetime"));
    }


    // 지점의 주입 내역 조회
    public List<FillLog> getBranchLogs(Long branchId) {
        return fillLogRepository.findByBranchId(branchId);
    }

    // 주입 내역 조회 (지사 관리자)
    public List<FillLogDto> getMyBranchLogs() {
        Long id = SecurityUtil.getCurrentDependentId().orElse(null);
        List<FillLog> fillLogs = fillLogRepository.findByBranchId(id);
        return fillLogToDto(fillLogs);
    }


    // 지점의 월 주입 통계
    public List<LogReportDto> getGraphData(Long branchId) {
        List<LogReportDto> logReportDto = fillLogRepository.findByBranchIdGroupByMonthWithJPQL(branchId);
        return logReportDto;
    }

    // 월 주입 통계 (지점 관리자)
    public List<LogReportDto> getMyGraphData() {
        Long id = SecurityUtil.getCurrentDependentId().orElse(null);
        List<LogReportDto> logReportDto = fillLogRepository.findByBranchIdGroupByMonthWithJPQL(id);
        return logReportDto;
    }

    public List<LogReportDto> getMyGraphDataByCarNumber(String carNumber) {
        List<LogReportDto> logReportDto = fillLogRepository.findByCarNumberGroupByMonthWithJPQL(carNumber);
        return logReportDto;
    }




    private List<FillLogDto> fillLogToDto(List<FillLog> fillLogs) {
        List<FillLogDto> fillLogDtoList = new ArrayList<>();
        fillLogs.stream().forEach(
                l -> fillLogDtoList.add(FillLogDto.builder()
                        .amount(l.getAmount())
                        .carNumber(l.getCarNumber())
                        .datetime(l.getDatetime())
                        .product(l.getProduct())
                        .pumpId(l.getPumpId())
                        .username(l.getUsername())
                        .build())
        );
        return fillLogDtoList;
    }
}
