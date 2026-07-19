package com.example.crudSpringBoot.GlobalExecptionHandler;

import com.example.crudSpringBoot.dto.ExecptionDto;
import com.example.crudSpringBoot.dto.ValidationExecptionResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExecptionHandler {

      @ExceptionHandler(RuntimeException.class)
      public ResponseEntity<ExecptionDto> handleRuntimeExecption(RuntimeException ex, HttpServletRequest request){

             ExecptionDto execptionDto = new ExecptionDto(
                   LocalDateTime.now(),
                   HttpStatus.NOT_FOUND.value(),
                   HttpStatus.NOT_FOUND.getReasonPhrase(),
                   ex.getMessage(),
                   request.getRequestURI()
             );

          return ResponseEntity
                  .status(HttpStatus.NOT_FOUND)
                  .body(execptionDto);

      }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExecptionDto> handleGenericExecption(Exception ex, HttpServletRequest request){

        ExecptionDto execptionDto = new ExecptionDto(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );


        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(execptionDto);
    }

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ExecptionDto> handleResourceNotFoundExecption(ResourceNotFound ex,HttpServletRequest request){

        ExecptionDto execptionDto = new ExecptionDto(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );


        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(execptionDto);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExecptionResponseDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,HttpServletRequest request){

          Map<String, String> fielderrors = new HashMap<>();

          ex.getBindingResult().getFieldErrors()
                  .forEach(error -> fielderrors.put(error.getField(),error.getDefaultMessage()));

        ValidationExecptionResponseDto validationExecptionResponseDto = new ValidationExecptionResponseDto(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "Validation Failed",
                request.getRequestURI(),
                fielderrors
        );


        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(validationExecptionResponseDto);
    }

}
