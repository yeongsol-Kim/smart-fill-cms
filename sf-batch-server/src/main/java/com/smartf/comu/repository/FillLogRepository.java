package com.smartf.comu.repository;

import com.smartf.comu.domain.FillLog;
import com.smartf.comu.dto.LogReportDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FillLogRepository extends JpaRepository<FillLog, Long> {

    List<FillLog> findByBranchId(Long branchId);

    @Query(value =
            "SELECT " +
            "new com.smartf.comu.dto.LogReportDto(MONTH(l.datetime) as month, SUM(l.amount) as sumAmount) " +
            "FROM FillLog l " +
            "GROUP BY MONTH(l.datetime)"
    )
    List<LogReportDto> findGroupByMonthWithJPQL();

    @Query(value =
            "SELECT " +
                    "new com.smartf.comu.dto.LogReportDto(MONTH(l.datetime) as month, SUM(l.amount) as sumAmount) " +
                    "FROM FillLog l " +
                    "WHERE l.branchId = :id " +
                    "GROUP BY MONTH(l.datetime)"
    )
    List<LogReportDto> findByBranchIdGroupByMonthWithJPQL(Long id);

    @Query(value =
            "SELECT " +
                    "new com.smartf.comu.dto.LogReportDto(MONTH(l.datetime) as month, SUM(l.amount) as sumAmount) " +
                    "FROM FillLog l " +
                    "WHERE l.carNumber = :id " +
                    "GROUP BY MONTH(l.datetime)"
    )
    List<LogReportDto> findByCarNumberGroupByMonthWithJPQL(String id);

}
