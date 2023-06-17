package com.reto.GestionDeTareas.Controllers.advices;

import com.reto.GestionDeTareas.Controllers.dtos.response.BaseResponse;
import com.reto.GestionDeTareas.Controllers.exeptions.BadRequestExeption;
import com.reto.GestionDeTareas.Controllers.exeptions.InternalServerErrorExeption;
import com.reto.GestionDeTareas.Controllers.exeptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MultiExceptionHandler {

    @ExceptionHandler(BadRequestExeption.class)
    private ResponseEntity<BaseResponse> handlerDataIntegrityViolationExeption(BadRequestExeption exception){
       BaseResponse errorResponse = BaseResponse.builder()
               .message(exception.getLocalizedMessage())
               .success(false)
               .httpStatus(HttpStatus.BAD_REQUEST).build();
               return new ResponseEntity<>(errorResponse,errorResponse.getHttpStatus());
    }

    @ExceptionHandler(InternalServerErrorExeption.class)
    private ResponseEntity<BaseResponse> handleOperationNotSupportedException(InternalServerErrorExeption exception) {
        BaseResponse errorResponse = BaseResponse.builder()
                .message(exception.getLocalizedMessage())
                .success(false)
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }

    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<BaseResponse> handleNotFoundException(NotFoundException exception) {
        BaseResponse errorResponse = BaseResponse.builder()
                .message(exception.getLocalizedMessage())
                .success(false)
                .httpStatus(HttpStatus.NOT_FOUND)
                .build();
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }
}
