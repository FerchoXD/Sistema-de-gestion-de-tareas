package com.reto.GestionDeTareas.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reto.GestionDeTareas.Controllers.dtos.request.CreateTaskRequest;
import com.reto.GestionDeTareas.Controllers.dtos.request.UpdateTaskRequest;
import com.reto.GestionDeTareas.Controllers.dtos.response.BaseResponse;
import com.reto.GestionDeTareas.Controllers.dtos.response.GetTaskResponse;
import com.reto.GestionDeTareas.Controllers.exeptions.BadRequestExeption;
import com.reto.GestionDeTareas.Controllers.exeptions.InternalServerErrorExeption;
import com.reto.GestionDeTareas.Controllers.exeptions.NotFoundException;
import com.reto.GestionDeTareas.Repositories.ITaskRepository;
import com.reto.GestionDeTareas.Services.interfaces.ITaskService;
import com.reto.GestionDeTareas.entities.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class TaskServiceImpl implements ITaskService {
    @Autowired
    private ITaskRepository repository;


    //visuaiza todas las tareas listadas
    @Override
    public BaseResponse getAllTasks(){
        try {
            List<GetTaskResponse> list = repository
                    .findAll()
                    .stream()
                    .map(this::from)
                    .collect(Collectors.toList());
            BaseResponse baseResponse = BaseResponse.builder()
                    .data(list)
                    .message("Tasks listed correctly")
                    .success(true)
                    .httpStatus(HttpStatus.OK)
                    .build();
            return baseResponse;
        }catch(Exception exception){
            throw new InternalServerErrorExeption("Failed to fetch tasks");
        }
    }

    //visualiza la tarea por id
    public BaseResponse getTaskById(Long id){
        validateId(id);
        try {
            GetTaskResponse taskResponse = from(id);
            BaseResponse baseResponse = BaseResponse.builder()
                    .data(taskResponse)
                    .message("Task listed correctly")
                    .success(true)
                    .httpStatus(HttpStatus.OK)
                    .build();
            return baseResponse;
        }catch (Exception exception){
           throw new InternalServerErrorExeption("Failed to fetch task");
        }
    }

    //busca la tarea con el id y devuelve el objeto de la tarea con el id encontrado
    private GetTaskResponse from (Long id){
        return repository.findById(id)
                .map(this::from)
                .orElseThrow(()->new NotFoundException("The Taks does not exits"));
    }

    //crea una nueva tarea
    @Override
    public BaseResponse createTask(CreateTaskRequest request) {
        if (request.getTitle().isEmpty() ||  request.getDue_date().equals("") || request.getDescription().isEmpty() ) {
            throw new BadRequestExeption("All fields are required");
        } else if (request.getTitle() == null ||request.getDue_date() == null || request.getDescription() == null) {
            throw new BadRequestExeption("Field not provided");
        } else if (request.getDescription().length() > 1000) {
            throw new BadRequestExeption("Description exceeds maximum length");
        } else
            try {
        {
                Task task = from(request);
                return BaseResponse.builder()
                        .data(from(repository.save(task)))
                        .message("Task created correctly")
                        .success(Boolean.TRUE)
                        .httpStatus(HttpStatus.CREATED)
                        .build();}
            } catch (Exception exception) {
                throw new InternalServerErrorExeption("Failed to create task");
            }
    }


    //actualiza una tarea por id
    @Override
    public BaseResponse updateTask(Long id, UpdateTaskRequest request) {
        validateId(id);

        if (request.getTitle().isEmpty() || request.getDue_date() == null || request.getDue_date().equals("") || request.getDescription().isEmpty()) {
            throw new BadRequestExeption("Field is required");
        } else if (request.getDescription().length() > 1000) {
            throw new BadRequestExeption("Description exceeds maximum length");
        }else {
            try {
                Task UpdateTask = from(request);
                UpdateTask.setId(id);

                return BaseResponse.builder()
                        .data(from(repository.save(UpdateTask)))
                        .message("Task Update correctly")
                        .success(Boolean.TRUE)
                        .httpStatus(HttpStatus.OK).build();

            } catch (Exception exception) {
                throw new InternalServerErrorExeption("Failed to update task");
            }
        }
    }

    public BaseResponse deleteTask (Long id){
        validateId(id);
        try {
            //muestra mensaje predeterminado
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonData;
            try {
                jsonData = objectMapper.writeValueAsString("Deleted Task");
            } catch (JsonProcessingException e) {
                jsonData = ""; // En caso de error al convertir a JSON
            }
            BaseResponse baseResponse = BaseResponse.builder()
                    .data(jsonData)
                    .message("Task deleted successfully")
                    .success(true)
                    .httpStatus(HttpStatus.OK)
                    .build();
            repository.deleteById(id);
            return baseResponse;
        }catch(Exception exception){
            throw new InternalServerErrorExeption("Failed to delete task");
        }
    }

    //convierte un objeto Task a GetTaskResponse y lo devuelve
    private GetTaskResponse from (Task task){
        GetTaskResponse response = new GetTaskResponse();
        response.setId(task.getId());
        response.setTitle(task.getTitle());
        response.setDescription(task.getDescription());
        response.setCompleted(task.isCompleted());
        response.setDue_date(task.getDue_date());
        response.setComments(task.getComments());
        response.setResponsible(task.getResponsible());
        response.setTag(task.getTag());
        return response;
    }

    //convierte un objeto CreateTaskRequest a Task y lo devuelve
    private Task from (CreateTaskRequest request){
        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setCompleted(request.isCompleted());
        task.setDue_date(request.getDue_date());
        task.setComments(request.getComments());
        task.setResponsible(request.getResponsible());
        task.setTag(request.getTag());

        return task;
    }

    //convierte un objeto UpdateTaskRequest a Task y lo devuelve
    private Task from (UpdateTaskRequest request){
        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setCompleted(request.isCompleted());
        task.setDue_date(request.getDue_date());
        task.setComments(request.getComments());
        task.setResponsible(request.getResponsible());
        task.setTag(request.getTag());
        return task;
    }

    //valida que el id exista
    private Task validateId(Long id){
        Task existingTask = repository.findById(id).orElse(null);
        if (existingTask == null) {
            throw new NotFoundException("Task not found");}
        return existingTask;
    }
}
