package com.example.run_scheduler.schedulerjob;

import com.example.run_scheduler.entity.ResultJobInfoEntity;
import com.example.run_scheduler.service.JobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@EnableScheduling
@Service
@Slf4j
public class RunnerSchedulerJob {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private JobService jobService;

    @Autowired
    private JobManager jobManager;

    @Scheduled(fixedDelay = 30000, initialDelay = 15000)
    public void runTask() {

        log.info("runTask запущен" + LocalDateTime.now());

        List<ResultJobInfoEntity> resultList = jobService.getResultListByStatus();

        if (!resultList.isEmpty()) {

            for (ResultJobInfoEntity job : resultList) {

                if (!job.getValue().isEmpty()) {

                    createThread(job);
                }
            }
        } else log.info("отсутствуют задания для запуска" + LocalDateTime.now());
    }

    private void createThread(ResultJobInfoEntity job) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                log.info("Поток с джобой " + job.getJobName() + " запущен");

                String value = job.getValue();

                long before = System.currentTimeMillis();
                String actualStartTime = String.valueOf(LocalDateTime.now());

                try {
                    jdbcTemplate.execute(value);
                } catch (RuntimeException e) {
                    job.setErrMessage("выполнен с ошибкой " + e.getMessage());
                }

                String actualEndTime = String.valueOf(LocalDateTime.now());
                long after = System.currentTimeMillis();
                long runDuration = (after - before) / 1000;
                jobManager.saveToBase(job, actualStartTime, actualEndTime, runDuration, job.getErrMessage());
                log.info(">>>джоб " + job.getJobName() + " выполнен");

                log.info("Поток с джобой " + job.getJobName() + " завершён");

                Thread.currentThread().interrupt();
            }
        });

        thread.start();
    }

}
