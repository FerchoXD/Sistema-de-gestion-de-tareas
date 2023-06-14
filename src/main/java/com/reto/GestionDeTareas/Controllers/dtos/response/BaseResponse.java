package com.reto.GestionDeTareas.Controllers.dtos.response;

import lombok.Getter;
import lombok.Builder;
import org.springframework.http.HttpStatus;
@Getter @Builder
public class BaseResponse {
    private Object data;
    private String message;
    private Boolean success;
    private HttpStatus httpStatus;
}
