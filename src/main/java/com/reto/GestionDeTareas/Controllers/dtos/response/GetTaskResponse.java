package com.reto.GestionDeTareas.Controllers.dtos.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter @Setter
public class GetTaskResponse {
    private Long id;
    private String title;
    private String description;
    private boolean completed;
    private LocalDate due_date;
    private String comments;
    private String responsible;
    private String tag;
}
