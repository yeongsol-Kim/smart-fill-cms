package hello.hellospring.repository;

import hello.hellospring.domain.FillLog;

import java.util.List;

public interface FillLogRepository {
    FillLog save(FillLog fillLog);
    List<FillLog> findAll();
}
