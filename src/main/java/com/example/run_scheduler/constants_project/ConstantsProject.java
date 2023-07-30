package com.example.run_scheduler.constants_project;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ConstantsProject {

    public static final String API = "/api";
    public static final String SCHEDULER_RESULT = API + "/scheduler_result";
    public static final String SCHEDULER_LIST = API + "/scheduler_list";
    public static final String SCHEDULER_LIST_SET_IS_ACTIV_PRIORITY = API + "/scheduler_list_set_is_activ_priority";
    public static final String SCHEDULER_LIST_ADD_NEW_JOB = API + "/scheduler_list_add_new_job";
    public static final String RUNNING = API + "/running";

    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    public static final DateTimeFormatter DAY_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);

    public static final String STATUS_RUNNING = "running";
    public static final String STATUS_SUCCESS = "success";
    public static final String STATUS_ERROR = "error";

    public static final String IS_ACTIVE_PARAM = "isActive";
    public static final String PRIORITY_PARAM = "priority";
    public static final String JOB_NAME = "jobName";


    public static final String IS_ACTIVE_TRUE = "true";
    public static final String IS_ACTIVE_FALSE = "false";
    public static final String PRIORITY_NOW = "now";
    public static final String PRIORITY_SCHEDULE = "schedule";

    public static final String INTERVAL_VALUE_DAILY = "daily";
    public static final String INTERVAL_VALUE_MONTHLY = "monthly";
    public static final String INTERVAL_VALUE_WEEKLY = "weekly";
    public static final String INTERVAL_VALUE_HOURLY = "hourly";
    public static final String INTERVAL_VALUE_MINUTELY = "minutely";

    public static final String JOB_NOT_FOUND_EXCEPTION = "Job not found";
    public static final String JOB_INTERVAL_TIME_EXCEPTION = "Job execution time exceeds start interval";

    public static final String VALID_JOB_NAME_ONLY_LATIN_PATTERN = "^[a-zA-Z]+$";
    public static final String VALID_IS_ACTIVE_VALUE_PATTERN = "^(true|false)$";
    public static final String PRIORITY_VALUE_PATTERN = "^(schedule|now)$";


}
