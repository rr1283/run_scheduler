package com.example.run_scheduler.controller;


import com.example.run_scheduler.dto.SchedulerListDto;
import com.example.run_scheduler.entity.PlanJobInfoEntity;
import com.example.run_scheduler.entity.ResultJobInfoEntity;
import com.example.run_scheduler.exception.ValidFildsException;
import com.example.run_scheduler.service.JobService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.run_scheduler.constants_project.ConstantsProject.*;
import static com.example.run_scheduler.exception.PrepareJobErrorsImpl.NOT_FOUND_PRIORITY_AND_IS_ACTIVE_EXCEPTION;

@RestController
public class SchedulerListController {

    @Autowired
    private JobService runSchedulerService;

    @Operation(summary = "список результатов")
    @GetMapping(SCHEDULER_RESULT)
    public @ResponseBody
    List<ResultJobInfoEntity> schedulerResultEntities() {
        return runSchedulerService.getResultList();
    }

    @Operation(summary = "список джобов")
    @GetMapping(SCHEDULER_LIST)
    public @ResponseBody
    List<PlanJobInfoEntity> schedulerListEntities() {
        return runSchedulerService.getSchedulerList();
    }

    @Operation(summary = "редактирование приоритета и активация джобы")
    @Parameter(name = "id", description = "редактируемая джоба", example = "1")
    @Parameter(name = "isActive", description = "активность", example = "true")
    @Parameter(name = "priority", description = "приоритет", example = "now")
    @GetMapping(SCHEDULER_LIST_SET_IS_ACTIV_PRIORITY)
    public void setIsActivAndPriority(@RequestParam(required = false) String isActive,
                                      @RequestParam(required = false) String priority,
                                      @RequestParam Long id) {

        if(isActive == null && priority == null){
            throw new ValidFildsException(NOT_FOUND_PRIORITY_AND_IS_ACTIVE_EXCEPTION);
        }

        if (isActive != null) {
            runSchedulerService.setIsActivePriority(IS_ACTIVE_PARAM, isActive, id);
        }

        if (priority != null) {
            runSchedulerService.setIsActivePriority(PRIORITY_PARAM, priority, id);
        }
    }

    @Operation(summary = "регистрация новой джобы")
    @PostMapping(SCHEDULER_LIST_ADD_NEW_JOB)
    public void addNewJob(@Valid @RequestBody SchedulerListDto schedulerListDto) {
        runSchedulerService.addNewJobForSchedulerList(schedulerListDto);
    }

}
