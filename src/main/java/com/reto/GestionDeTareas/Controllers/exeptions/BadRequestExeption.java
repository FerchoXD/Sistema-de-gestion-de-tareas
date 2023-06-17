package com.reto.GestionDeTareas.Controllers.exeptions;

public class BadRequestExeption extends RuntimeException{
    public BadRequestExeption(String message) {
        super(message);
    }
}
