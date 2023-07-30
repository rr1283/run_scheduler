package com.example.run_scheduler.schedulerjob;

import com.example.run_scheduler.entity.PlanJobInfoEntity;
import com.example.run_scheduler.repository.SchedulerResultRepository;
import com.example.run_scheduler.service.JobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static com.example.run_scheduler.constants_project.ConstantsProject.*;

@EnableScheduling
@Service
@Slf4j
public class PrepareSchedulerJob {

    @Autowired
    private SchedulerResultRepository schedulerResultRepository;

    @Autowired
    private JobService runSchedulerService;

    @Autowired
    private JobManager jobManager;


    // todo следующий джоб ждёт завершения предыдущего
    // todo Обработка ошибок. Если ошибка - залогироваться и продолжать выполнение. => perform databasescheduler.test(1)
    // todo таблица schedulerResult наполняется после отработки всех джобов. Добавить статус running
    // todo если отсутствует END_TIME падает с NullPointerException
    // todo вместо saveToBase найти вариант с insert => update


    /*
     * Подготовка списка заданий
     * Запуск каждые 30 секунд
     */
    @Scheduled(fixedDelay = 30000)
    public void runPrepareTask() {

        log.info("runPrepareTask запущен" + LocalDateTime.now());

        // Заполняем лист джобов, отсортированных по времени
        List<PlanJobInfoEntity> jobList = runSchedulerService.getSchedulerList();

        if (!jobList.isEmpty()) {

            for (PlanJobInfoEntity job : jobList) {

                String intervalVal = job.getIntervalVal();

                LocalDate startDate = LocalDate.parse(job.getStartDate(), DATE_TIME_FORMATTER);

                if (job.getPriority().equals(PRIORITY_NOW)) {
                    prepareJobPriorityNow(job);
                }

                if (intervalVal.equals(INTERVAL_VALUE_DAILY)) {


                    if (!startDate.equals(getCurrentDay())) {
                        continue;
                    }
                    prepareJobDailyInterval(job);

                } else if (intervalVal.equals(INTERVAL_VALUE_WEEKLY)) {
                    if (!startDate.equals(getCurrentDay())) {
                        continue;
                    }
                    prepareJobWeeklyInterval(job);

                } else if (intervalVal.equals(INTERVAL_VALUE_MONTHLY)) {
                    if (!startDate.equals(getCurrentDay())) {
                        continue;
                    }
                    prepareJobMonthlyInterval(job);


                } else if (intervalVal.equals(INTERVAL_VALUE_MINUTELY)) {

                    if (!startDate.equals(getCurrentDay())) {
                        continue;
                    }
                    prepareJobMinutelyInterval(job);

                } else if (intervalVal.equals(INTERVAL_VALUE_HOURLY)) {

                    if (!startDate.equals(getCurrentDay())) {
                        continue;
                    }
                    prepareJobHourlyInterval(job);
                }
            }
        } else log.info("отсутствуют задания для запуска" + LocalDateTime.now());
    }

    private void prepareJobDailyInterval(PlanJobInfoEntity job) {

        if (getStartTimeJob(job).isBefore(getStartTimeNow())) {
            if (runSchedulerService.checkStatusJob(job)) {
                jobManager.saveToBasePrepareJob(job);
                PlanJobInfoEntity schedulerEntity = runSchedulerService.getSchedulerEntity(job.getId());
                schedulerEntity.setStartDate(DATE_TIME_FORMATTER.format(LocalDate.now().plusDays(Integer.parseInt(job.getInterval()))));
                runSchedulerService.saveToBase(schedulerEntity);
            } else jobManager.saveWhithError(job.getId());
        }
    }

    private void prepareJobWeeklyInterval(PlanJobInfoEntity job) {

        if (getStartTimeJob(job).isBefore(getStartTimeNow())) {
            jobManager.saveToBasePrepareJob(job);
            PlanJobInfoEntity schedulerEntity = runSchedulerService.getSchedulerEntity(job.getId());
            schedulerEntity.setStartDate(DATE_TIME_FORMATTER.format(LocalDate.now().plusDays(7L * Integer.parseInt(job.getInterval()))));
            runSchedulerService.saveToBase(schedulerEntity);
        }
    }

    private void prepareJobMonthlyInterval(PlanJobInfoEntity job) {

        if (getStartTimeJob(job).isBefore(getStartTimeNow())) {
            jobManager.saveToBasePrepareJob(job);
            PlanJobInfoEntity schedulerEntity = runSchedulerService.getSchedulerEntity(job.getId());
            schedulerEntity.setStartDate(DATE_TIME_FORMATTER.format(LocalDate.now().plusMonths(Integer.parseInt(job.getInterval()))));
            runSchedulerService.saveToBase(schedulerEntity);
        }
    }

    private void prepareJobMinutelyInterval(PlanJobInfoEntity job) {

        if (getStartTimeJob(job).isBefore(getStartTimeNow())) {

            if (runSchedulerService.checkStatusJob(job)) {
                jobManager.saveToBasePrepareJob(job);
                PlanJobInfoEntity schedulerEntity = runSchedulerService.getSchedulerEntity(job.getId());
                LocalTime timeAfterPrepare = getTime(job.getTime()).plusMinutes(Long.parseLong(job.getInterval()));
                schedulerEntity.setTime(timeAfterPrepare.toString());
                runSchedulerService.saveToBase(schedulerEntity);
            } else jobManager.saveWhithError(job.getId());
        }
    }

    private void prepareJobHourlyInterval(PlanJobInfoEntity job) {

        if (getStartTimeJob(job).isBefore(getStartTimeNow())) {

            if (runSchedulerService.checkStatusJob(job)) {
                jobManager.saveToBasePrepareJob(job);
                PlanJobInfoEntity schedulerEntity = runSchedulerService.getSchedulerEntity(job.getId());
                LocalTime timeAfterPrepare = getTime(job.getTime()).plusHours(Long.parseLong(job.getInterval()));
                schedulerEntity.setTime(timeAfterPrepare.toString());
                runSchedulerService.saveToBase(schedulerEntity);
            } else jobManager.saveWhithError(job.getId());
        }
    }

    private void prepareJobPriorityNow(PlanJobInfoEntity job) {
        jobManager.saveToBasePrepareJob(job);
        PlanJobInfoEntity schedulerEntity = runSchedulerService.getSchedulerEntity(job.getId());
        schedulerEntity.setPriority(PRIORITY_SCHEDULE);
        runSchedulerService.saveToBase(schedulerEntity);
    }

    private LocalTime getTime(String stringFormatTimeNow) {
        LocalTime localTime = LocalTime.parse(stringFormatTimeNow);
        return localTime;
    }

    private String getCurrentTime(LocalTime localTime) {
        return TIME_FORMATTER.format(localTime);
    }

    private LocalDate getCurrentDay() {
        return LocalDate.parse(DAY_FORMATTER.format(LocalDateTime.now()));
    }

    private LocalTime getStartTimeNow() {
        LocalTime timeNow = getTime(getCurrentTime(LocalTime.now()));
        return timeNow;
    }

    private LocalTime getStartTimeJob(PlanJobInfoEntity job) {
        LocalTime startTimeJob = getTime(job.getTime());
        return startTimeJob;
    }

}

