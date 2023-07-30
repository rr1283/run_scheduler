package com.example.run_scheduler.dto;

import lombok.Data;

@Data
public class SchedulerListDto {

    private Long id;
    private String jobName;
    private String value;
    private String startDate;
    private String endDate;
    private String interval;
    private String intervalVal;
    private String time;
    private String priority;
    private String isActive;

}
