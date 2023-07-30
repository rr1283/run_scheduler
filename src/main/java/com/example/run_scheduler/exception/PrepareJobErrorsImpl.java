package com.example.run_scheduler.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.example.run_scheduler.constants_project.ConstantsProject.*;

@Getter
@RequiredArgsConstructor
public enum PrepareJobErrorsImpl implements ErrorApplication{

    NOT_FOUND_PRIORITY_AND_IS_ACTIVE_EXCEPTION(-2000,"Отсутствуют значения для полей"),
    VALID_IS_ACTIVE_EXCEPTION(-2001,"Введены не корректные параметры в " + IS_ACTIVE_PARAM),
    VALID_PRIORITY_EXCEPTION(-2002,"Введены не корректные параметры в " + PRIORITY_PARAM),
    NOT_FOUND_OBJECT_EXCEPTION(-2003,"Объект не найден"),
    VALID_JOB_NAME_EXCEPTION(-2004,"Введены не корректные параметры в " + JOB_NAME);

    private final int code;
    private final String message;

}
