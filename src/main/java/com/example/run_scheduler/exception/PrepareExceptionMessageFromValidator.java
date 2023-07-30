package com.example.run_scheduler.exception;

import static com.example.run_scheduler.constants_project.ConstantsProject.*;

public class PrepareExceptionMessageFromValidator {

    public static final String IS_ACTIVE_EXCEPTION = "Введены не корректные параметры в " + IS_ACTIVE_PARAM + " должен быть 'true' или 'false' в нижнем регистре";
    public static final String PRIORITY_EXCEPTION = "Введены не корректные параметры в " + PRIORITY_PARAM + " должен быть 'schedule' или 'now' в нижнем регистре";
    public static final String JOB_NAME_EXCEPTION = "Введены не корректные параметры в " + JOB_NAME + " допустимы только латинские буквы";


}
