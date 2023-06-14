package com.reto.GestionDeTareas.Controllers;

import com.reto.GestionDeTareas.Services.TaskServiceImpl;
import com.reto.GestionDeTareas.Services.interfaces.ITaskService;
import com.reto.GestionDeTareas.entities.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private ITaskService service;

    @GetMapping
    public List<Task> getAllTasks(){
        return service.getAllTasks();
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id){
        return service.getTaskById(id);
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return service.createTask(task);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id,@RequestBody Task task){
        return service.updateTask(id,task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        service.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
