package com.reto.GestionDeTareas.Services.interfaces;

import com.reto.GestionDeTareas.Controllers.dtos.request.CreateTaskRequest;
import com.reto.GestionDeTareas.Controllers.dtos.request.UpdateTaskRequest;
import com.reto.GestionDeTareas.Controllers.dtos.response.BaseResponse;
import com.reto.GestionDeTareas.Controllers.dtos.response.GetTaskResponse;
import com.reto.GestionDeTareas.entities.Task;
import java.util.List;

public interface ITaskService {
    BaseResponse getAllTasks();
    BaseResponse getTaskById(Long id);

    BaseResponse createTask (CreateTaskRequest request);

    BaseResponse updateTask(Long id, UpdateTaskRequest request);

    BaseResponse deleteTask (Long id);
}