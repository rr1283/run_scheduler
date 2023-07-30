package com.example.run_scheduler.schedulerjob;

import com.example.run_scheduler.entity.PlanJobInfoEntity;
import com.example.run_scheduler.entity.ResultJobInfoEntity;
import com.example.run_scheduler.repository.SchedulerResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.run_scheduler.constants_project.ConstantsProject.*;

@Service
public class JobManager {

    @Autowired
    private SchedulerResultRepository schedulerResultRepository;

    void saveToBase(ResultJobInfoEntity rj, String actualStartTime, String actualEndTime, long duration, String errMessage) {
        ResultJobInfoEntity schedulerResultEntity = new ResultJobInfoEntity();
        schedulerResultEntity.setId(rj.getId());
        schedulerResultEntity.setJobName(rj.getJobName());
        schedulerResultEntity.setRunDuration(String.valueOf(duration));
        schedulerResultEntity.setValue(rj.getValue());
        schedulerResultEntity.setActualStartTime(actualStartTime);
        schedulerResultEntity.setActualEndTime(actualEndTime);
        schedulerResultEntity.setStatus(getStatus(errMessage));
        schedulerResultEntity.setErrMessage(errMessage);
        schedulerResultRepository.saveAndFlush(schedulerResultEntity);
    }

    void saveToBasePrepareJob(PlanJobInfoEntity sl) {
        ResultJobInfoEntity schedulerResultEntity = new ResultJobInfoEntity();
        schedulerResultEntity.setId(sl.getId());
        schedulerResultEntity.setJobName(sl.getJobName());
        schedulerResultEntity.setRunDuration(null);
        schedulerResultEntity.setValue(sl.getValue());
        schedulerResultEntity.setActualStartTime(sl.getTime());
        schedulerResultEntity.setActualEndTime(null);
        schedulerResultEntity.setStatus("running");
        schedulerResultEntity.setErrMessage(null);
        schedulerResultRepository.save(schedulerResultEntity);
    }

    void saveWhithError(Long id) {
        ResultJobInfoEntity job = schedulerResultRepository.findById(id).orElseThrow(() -> new RuntimeException(JOB_NOT_FOUND_EXCEPTION));
        job.setErrMessage(JOB_INTERVAL_TIME_EXCEPTION);
        schedulerResultRepository.save(job);
    }


    private String getStatus(String errMessage) {
        if (errMessage == null) {
            return STATUS_SUCCESS;
        } else return STATUS_ERROR;
    }

}
