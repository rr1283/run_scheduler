package com.example.run_scheduler.validator;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.example.run_scheduler.constants_project.ConstantsProject.*;
import static com.example.run_scheduler.exception.PrepareJobErrorsImpl.*;

@Component
@ConditionalOnProperty(name = "interceptor.enabled", havingValue = "true")
public class ParamsValidatorInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (request.getParameterMap().containsKey(IS_ACTIVE_PARAM) && !checkIsActiveValid(request)) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.getWriter().write(VALID_IS_ACTIVE_EXCEPTION.getMessage());
            return false;
        }

        if (request.getParameterMap().containsKey(PRIORITY_PARAM) && !checkPriorityValid(request)) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.getWriter().write(VALID_PRIORITY_EXCEPTION.getMessage());
            return false;
        }

//        HandlerMethod handler1 = (HandlerMethod) handler;
//        SchedulerListController controller = (SchedulerListController) handler1.getBean();


        if (request.getParameterMap().containsKey(JOB_NAME) && !checkJobNameValid(request)) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.getWriter().write(VALID_JOB_NAME_EXCEPTION.getMessage());
            return false;
        }

        return true;
    }

    private boolean checkIsActiveValid(HttpServletRequest request) {

        String isActiveStatus = Arrays.stream(request.getParameterMap().get(IS_ACTIVE_PARAM)).collect(Collectors.toList()).get(0);

        if (isActiveStatus.equals(IS_ACTIVE_TRUE) || isActiveStatus.equals(IS_ACTIVE_FALSE)) {
            return true;
        }
        return false;
    }

    private boolean checkPriorityValid(HttpServletRequest request) {
        String isActiveStatus = Arrays.stream(request.getParameterMap().get(PRIORITY_PARAM)).collect(Collectors.toList()).get(0);

        if (isActiveStatus.equals(PRIORITY_NOW) || isActiveStatus.equals(PRIORITY_SCHEDULE)) {
            return true;
        }
        return false;
    }

    private boolean checkJobNameValid(HttpServletRequest request) {
        String jobName = Arrays.stream(request.getParameterMap().get(JOB_NAME)).collect(Collectors.toList()).get(0);
        Pattern pattern = Pattern.compile(ONLY_LATIN);
        Matcher matcher = pattern.matcher(jobName);
        if (jobName != null && matcher.matches()) {
            return true;
        }
        return false;
    }

}
