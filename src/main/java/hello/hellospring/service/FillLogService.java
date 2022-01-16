package hello.hellospring.service;

import hello.hellospring.domain.FillLog;
import hello.hellospring.repository.FillLogRepository;
import hello.hellospring.repository.MemberRepository;

import java.util.List;

public class FillLogService {

    private final FillLogRepository fillLogRepository;

    public FillLogService(FillLogRepository fillLogRepository) {
        this.fillLogRepository = fillLogRepository;
    }

    public Long join(FillLog fillLog) {
        fillLogRepository.save(fillLog);
        return fillLog.getId();
    }

    public List<FillLog> findFillLogs() {
        return fillLogRepository.findAll();
    }
}
