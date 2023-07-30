package com.example.run_scheduler.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "schedulerlist")
@Data
public class PlanJobInfoEntity {

    //  @SequenceGenerator(name = "scheduler_listSequence", sequenceName = "scheduler_list_sequence", allocationSize = 1)
    //  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "scheduler_listSequence")
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "job_name")
    private String jobName;

    @Column(name = "value")
    private String value;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "end_date")
    private String endDate;

    @Column(name = "interval")
    private String interval;

    @Column(name = "interval_val")
    private String intervalVal;

    @Column(name = "time")
    private String time;

    @Column(name = "priority")
    private String priority;

    @Column(name = "is_active")
    private String isActive;

}
