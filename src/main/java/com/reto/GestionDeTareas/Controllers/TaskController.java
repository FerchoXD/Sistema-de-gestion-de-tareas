package com.reto.GestionDeTareas.Controllers;

import com.reto.GestionDeTareas.Controllers.dtos.request.CreateTaskRequest;
import com.reto.GestionDeTareas.Controllers.dtos.request.UpdateTaskRequest;
import com.reto.GestionDeTareas.Controllers.dtos.response.BaseResponse;
import com.reto.GestionDeTareas.Controllers.dtos.response.CreateTaskResponse;
import com.reto.GestionDeTareas.Controllers.dtos.response.GetTaskResponse;
import com.reto.GestionDeTareas.Services.TaskServiceImpl;
import com.reto.GestionDeTareas.Services.interfaces.ITaskService;
import com.reto.GestionDeTareas.entities.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private ITaskService service;

    @GetMapping("/list")
    public ResponseEntity<BaseResponse> getAllTasks(){
        BaseResponse baseResponse = service.getAllTasks();
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping("/list/{id}")
    public ResponseEntity getTaskById(@PathVariable Long id){
        BaseResponse baseResponse = service.getTaskById(id);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PostMapping("/create")
    public ResponseEntity<BaseResponse> createTask(@RequestBody CreateTaskRequest request) {
        BaseResponse baseResponse = service.createTask(request);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BaseResponse> updateTask(@PathVariable Long id,@RequestBody UpdateTaskRequest request){
        BaseResponse baseResponse = service.updateTask(id,request);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BaseResponse> deleteTask(@PathVariable Long id) {
        BaseResponse baseResponse = service.deleteTask(id);
        return ResponseEntity.ok(baseResponse);
    }
}
