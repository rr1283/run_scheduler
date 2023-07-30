package com.example.run_scheduler.exception;

import lombok.Setter;

@Setter
public class ValidFildsException extends RuntimeException{

   private final int code;

   public ValidFildsException(ErrorApplication errorApplication){
       super(errorApplication.getMessage());
       this.code = errorApplication.getCode();
   }

}
