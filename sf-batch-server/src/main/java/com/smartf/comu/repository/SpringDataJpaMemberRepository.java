package com.smartf.comu.repository;

import com.smartf.comu.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long> {

    Member findByUsername(String aString);
    Member findByEmail(String email);
    List<Member> findByBranchId(Long branchId);

}
