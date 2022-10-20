package com.smartf.comu.repository;

import com.smartf.comu.domain.Reservoir;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservoirRepository extends JpaRepository<Reservoir, Long> {
    List<Reservoir> findByBranchId(Long branchId);
    Optional<Reservoir> findByIdAndBranchId(Long Id, Long BranchId);
}
