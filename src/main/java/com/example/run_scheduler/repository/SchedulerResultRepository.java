package com.example.run_scheduler.repository;

import com.example.run_scheduler.entity.ResultJobInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchedulerResultRepository extends JpaRepository<ResultJobInfoEntity, Long> {

    List<ResultJobInfoEntity> getResultJobInfoEntitiesByStatus(String value);

    ResultJobInfoEntity getResultJobInfoEntityByJobNameAndStatus(String jobName, String status);

}

