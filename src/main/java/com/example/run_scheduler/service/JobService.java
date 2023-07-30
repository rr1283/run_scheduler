package com.example.run_scheduler.service;

import com.example.run_scheduler.dto.SchedulerListDto;
import com.example.run_scheduler.entity.PlanJobInfoEntity;
import com.example.run_scheduler.entity.ResultJobInfoEntity;

import java.util.List;

public interface JobService {

    List<PlanJobInfoEntity> getSchedulerList();

    //Получаем список заданий
    List<ResultJobInfoEntity> getResultListByStatus();

    List<ResultJobInfoEntity> getResultList();

    PlanJobInfoEntity getSchedulerEntity(Long id);

    void saveToBase(PlanJobInfoEntity schedulerEntity);

    boolean checkStatusJob(PlanJobInfoEntity job);

    void setIsActivePriority(String param, String value, Long id);

    void addNewJobForSchedulerList(SchedulerListDto schedulerListDto);

}
