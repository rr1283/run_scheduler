package com.example.run_scheduler.repository;

import com.example.run_scheduler.entity.PlanJobInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchedulerRepository extends JpaRepository<PlanJobInfoEntity, Long> {

    @Query(value = "select p from PlanJobInfoEntity p where p.isActive = 'true'")
    List<PlanJobInfoEntity> getActiveJobList();

}
