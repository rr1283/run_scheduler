package com.example.run_scheduler.repository;

import com.example.run_scheduler.entity.PlanJobInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchedulerRepository extends JpaRepository<PlanJobInfoEntity, Long> {

}
