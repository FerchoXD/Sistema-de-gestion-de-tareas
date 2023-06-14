package com.reto.GestionDeTareas.Repositories;

import com.reto.GestionDeTareas.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ITaskRepository  extends JpaRepository<Task,Long> {

}
