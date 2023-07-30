package com.example.run_scheduler.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

import static com.example.run_scheduler.constants_project.ConstantsProject.*;
import static com.example.run_scheduler.exception.PrepareExceptionMessageFromValidator.*;

@Data
public class SchedulerListDto {

    private Long id;

    @Pattern(regexp = VALID_JOB_NAME_ONLY_LATIN_PATTERN, message = JOB_NAME_EXCEPTION)
    private String jobName;

    private String value;
    private String startDate;
    private String endDate;
    private String interval;
    private String intervalVal;
    private String time;

    @Pattern(regexp = PRIORITY_VALUE_PATTERN, message = PRIORITY_EXCEPTION)
    private String priority;

    @Pattern(regexp = VALID_IS_ACTIVE_VALUE_PATTERN, message = IS_ACTIVE_EXCEPTION)
    private String isActive;

}
