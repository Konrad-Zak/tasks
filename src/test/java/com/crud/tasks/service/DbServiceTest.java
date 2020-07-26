package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DbServiceTest {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    DbService dbService;

    @Test
    public void getAllTasks() {
        //Given
        Task taskOne = new Task("test_1","testing");
        Task taskTwo = new Task("test_2","testing 2");
        taskRepository.save(taskOne);
        taskRepository.save(taskTwo);

        //When
        List<Task> tasks = dbService.getAllTasks();

        //Then
        assertEquals(2,tasks.size());
        assertEquals(taskOne.getTitle(),tasks.get(0).getTitle());
        assertEquals(taskOne.getContent(),tasks.get(0).getContent());
        assertEquals(taskTwo.getTitle(),tasks.get(1).getTitle());
        assertEquals(taskTwo.getContent(),tasks.get(1).getContent());

        //CleanUp
        taskRepository.deleteAll();
    }

    @Test
    public void getTask() {
        //Given
        Task task = new Task("test_1","testing");
        taskRepository.save(task);

        //When
        Optional<Task> resultTask = dbService.getTask(task.getId());

        //Then
        assertTrue(resultTask.isPresent());
        assertEquals(task.getTitle(),resultTask.get().getTitle());
        assertEquals(task.getContent(),resultTask.get().getContent());

        //CleanUp
        taskRepository.deleteAll();
    }

    @Test
    public void saveTask() {
        //Given
        Task task = new Task("test_1","testing");

        //When
        dbService.saveTask(task);
        Optional<Task> resultTask = taskRepository.findById(task.getId());

        //Then
        assertTrue(resultTask.isPresent());

        //CleanUp
        taskRepository.deleteAll();
    }

    @Test
    public void deleteTask() {
        //Given
        Task task = new Task("test_1","testing");
        taskRepository.save(task);

        //When
        dbService.deleteTask(task.getId());
        Optional<Task> resultTask = taskRepository.findById(task.getId());

        //Then
        assertFalse(resultTask.isPresent());
    }
}