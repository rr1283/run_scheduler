package com.example.run_scheduler.service;

import com.example.run_scheduler.dto.SchedulerListDto;
import com.example.run_scheduler.entity.PlanJobInfoEntity;
import com.example.run_scheduler.entity.ResultJobInfoEntity;
import com.example.run_scheduler.exception.ValidFildsException;
import com.example.run_scheduler.mapper.JobMapper;
import com.example.run_scheduler.repository.SchedulerRepository;
import com.example.run_scheduler.repository.SchedulerResultRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.run_scheduler.constants_project.ConstantsProject.*;
import static com.example.run_scheduler.exception.PrepareJobErrorsImpl.NOT_FOUND_OBJECT_EXCEPTION;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    protected static final Logger log = LoggerFactory.getLogger(JobServiceImpl.class);

    private final SchedulerRepository schedulerListRepository;
    private final SchedulerResultRepository schedulerResultRepository;
    private final JobMapper jobMapper;


    //Получаем список заданий
    @Override
    public List<PlanJobInfoEntity> getSchedulerList() {
        List<PlanJobInfoEntity> schedulerListDtos = schedulerListRepository.getActiveJobList();
        List<PlanJobInfoEntity> resultList = schedulerListDtos.stream().sorted(Comparator.comparing(PlanJobInfoEntity::getTime)).collect(Collectors.toList());
        return resultList;
    }

    //Получаем список заданий готовых к выполнению отсортированнных по времени
    @Override
    public List<ResultJobInfoEntity> getResultListByStatus() {
        List<ResultJobInfoEntity> resultJobList = schedulerResultRepository.getResultJobInfoEntitiesByStatus(STATUS_RUNNING);
        List<ResultJobInfoEntity> resultList = resultJobList.stream().sorted(Comparator.comparing(ResultJobInfoEntity::getActualStartTime)).collect(Collectors.toList());
        return resultList;
    }

    //Получаем список заданий готовых к выполнению отсортированнных по времени
    @Override
    public List<ResultJobInfoEntity> getResultList() {
        List<ResultJobInfoEntity> resultJobList = schedulerResultRepository.findAll();
        List<ResultJobInfoEntity> resultList = resultJobList.stream().sorted(Comparator.comparing(ResultJobInfoEntity::getActualStartTime)).collect(Collectors.toList());
        return resultList;
    }

    //Получаем одно задание
    @Override
    public PlanJobInfoEntity getSchedulerEntity(Long id) {
        PlanJobInfoEntity job = schedulerListRepository.findById(id).get();
        return job;
    }

    //
    @Override
    public void saveToBase(PlanJobInfoEntity schedulerEntity) {
        schedulerListRepository.save(schedulerEntity);
    }

    @Override
    public boolean checkStatusJob(PlanJobInfoEntity job) {
        ResultJobInfoEntity result = schedulerResultRepository.getResultJobInfoEntityByJobNameAndStatus(job.getJobName(), STATUS_RUNNING);
        if (result == null) {
            return true;
        }
        return false;
    }

    @Override
    public void setIsActivePriority(String param, String value, Long id) {
        PlanJobInfoEntity planJob = schedulerListRepository.findById(id)
                .orElseThrow(() -> new ValidFildsException(NOT_FOUND_OBJECT_EXCEPTION)); //обработка Optional

        if (param.equals(IS_ACTIVE_PARAM)) {
            planJob.setIsActive(value);
            schedulerListRepository.save(planJob);
        }

        if (param.equals(PRIORITY_PARAM)) {
            planJob.setPriority(value);
            schedulerListRepository.save(planJob);
        }
    }

    @Override
    public void addNewJobForSchedulerList(SchedulerListDto schedulerListDto) {

        PlanJobInfoEntity planJobInfoEntity = jobMapper.jobDtoToJobEntity(schedulerListDto);
        schedulerListRepository.save(planJobInfoEntity);
    }

}
