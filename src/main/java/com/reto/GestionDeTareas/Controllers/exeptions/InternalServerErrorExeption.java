package com.reto.GestionDeTareas.Controllers.exeptions;

public class InternalServerErrorExeption extends RuntimeException {
    public InternalServerErrorExeption(String message) {
        super(message);
    }
}
