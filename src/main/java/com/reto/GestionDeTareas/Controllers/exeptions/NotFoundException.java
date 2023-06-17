package com.reto.GestionDeTareas.Controllers.exeptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }
}
