package com.reto.GestionDeTareas.Services;

import com.reto.GestionDeTareas.Repositories.ITaskRepository;
import com.reto.GestionDeTareas.Services.interfaces.ITaskService;
import com.reto.GestionDeTareas.entities.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static jdk.internal.joptsimple.internal.Strings.isNullOrEmpty;

@Service
public class TaskServiceImpl implements ITaskService {
    @Autowired
    private ITaskRepository repository;

    public List<Task> getAllTasks(){
        return repository.findAll();
    }

    public Task getTaskById(Long id){
        return repository.findById(id).orElse(null);
    }

    public Task createTask(Task task) {
        validateTask(task);

        return repository.save(task);
    }

    public Task updateTask (Long id,Task task){
        Task existingTask = repository.findById(id).orElse(null);
        if (existingTask == null){
            throw new IllegalArgumentException("tarea no encontrada");
        }
        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setCompleted(task.isCompleted());
        existingTask.setDueDate(task.getDueDate());
        existingTask.setComments(task.getComments());
        existingTask.setResponsible(task.getResponsible());

        return repository.save(existingTask);
    }

    public void deleteTask (Long id){
        repository.deleteById(id);
    }
    private void validateTask(Task task) {
        if (isNullOrEmpty(task.getTitle())) {
            throw new IllegalArgumentException("El título de la tarea no puede estar vacío");
        }
        if (isNullOrEmpty(task.getDescription())) {
            throw new IllegalArgumentException("La descripción de la tarea no puede estar vacía");
        }
        if (task.getDueDate() != null && task.getDueDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de entrega no puede ser anterior a la fecha actual.");
        }
    }

}
