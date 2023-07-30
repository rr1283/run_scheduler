//package com.example.run_scheduler.schedulerjob;
//
//import com.example.run_scheduler.entity.ResultJobInfoEntity;
//import com.example.run_scheduler.service.JobService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.List;
//
//@EnableScheduling
//@Component
//@Transactional
//public class StartJobScheduler {
//
//    public static final Logger log = LoggerFactory.getLogger(StartJobScheduler.class);
//
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    private JobService jobService;
//
//    @Autowired
//    private JobManager jobManager;
//
//    /*
//     * Запуск заданий
//     * Запуск каждые 30 секунд
//     */
//    @Scheduled(fixedDelay = 30000)
//    public void runTask() {
//
//        log.info(">>>Начало запуска заданий");
//
//        List<ResultJobInfoEntity> resultList = jobService.getResultList();
//
//        for (ResultJobInfoEntity o : resultList) {
//
//            long before = System.currentTimeMillis();
//            String actualStartTime = String.valueOf(LocalDateTime.now());
//
//
//            /**/
//            String errMessage = jdbcTemplateExecute(o.getValue(), o.getJobName());
//            /**/
//
//            String actualEndTime = String.valueOf(LocalDateTime.now());
//            jobManager.saveToBase(o, actualStartTime, actualEndTime, runDuration(before), errMessage);
//            log.info(">>>джоб " + o.getJobName() + " выполнен");
//        }
//    }
//
//
//    private long runDuration(long before) {
//        long after = System.currentTimeMillis();
//        return (after - before) / 1000;
//    }
//
//    private String jdbcTemplateExecute(String sqlJob, String jobName) {
//        try {
//            jdbcTemplate.execute(sqlJob);
//        } catch (RuntimeException e) {
//            log.error(">>>джоб " + jobName + " выполнен с ошибкой " + e.getMessage());
//            //throw new RuntimeException("выполнен с ошибкой").toString();
//            return String.valueOf(e);
//        }
//        return null;
//    }
//
//
//}
//
