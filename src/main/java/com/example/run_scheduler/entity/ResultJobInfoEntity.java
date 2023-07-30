package com.example.run_scheduler.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "schedulerresult")
@Data
public class ResultJobInfoEntity {

    //  @SequenceGenerator(name = "sequence", sequenceName = "schedulerresult_sequence", allocationSize = 1)
    //  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "schedulerresult_sequence")
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "job_name")
    private String jobName;

    @Column(name = "value")
    private String value;

    @Column(name = "run_duration")
    private String runDuration;

    @Column(name = "actual_start_time")
    private String actualStartTime;

    @Column(name = "actual_end_time")
    private String actualEndTime;

    @Column(name = "status")
    private String status;

    @Column(name = "err_message")
    private String errMessage;

}