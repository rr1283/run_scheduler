package com.example.run_scheduler.mapper;

import com.example.run_scheduler.dto.SchedulerListDto;
import com.example.run_scheduler.entity.PlanJobInfoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JobMapper {

    PlanJobInfoEntity jobDtoToJobEntity(SchedulerListDto schedulerListDto);

}
