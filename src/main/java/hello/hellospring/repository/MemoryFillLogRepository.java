package hello.hellospring.repository;

import hello.hellospring.domain.FillLog;
import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryFillLogRepository implements FillLogRepository {

    private static Map<Long, FillLog> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public FillLog save(FillLog fillLog) {
        fillLog.setId(++sequence);
        store.put(fillLog.getId(), fillLog);
        return fillLog;
    }

    @Override
    public List<FillLog> findAll() {
        return new ArrayList<>(store.values());
    }

}
