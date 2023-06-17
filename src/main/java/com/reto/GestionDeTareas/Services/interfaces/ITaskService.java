package com.reto.GestionDeTareas.Services.interfaces;

import com.reto.GestionDeTareas.Controllers.dtos.request.CreateTaskRequest;
import com.reto.GestionDeTareas.Controllers.dtos.request.UpdateTaskRequest;
import com.reto.GestionDeTareas.Controllers.dtos.response.BaseResponse;

public interface ITaskService {
    BaseResponse getAllTasks();
    BaseResponse getTaskById(Long id);

    BaseResponse createTask (CreateTaskRequest request);

    BaseResponse updateTask(Long id, UpdateTaskRequest request);

    BaseResponse deleteTask (Long id);
}