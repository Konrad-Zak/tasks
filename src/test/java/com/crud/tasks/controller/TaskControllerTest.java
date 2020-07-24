package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.exception.TaskNotFoundException;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskControllerTest {

    @Autowired
    private TaskController taskController;

    @Autowired
    private TaskRepository taskRepository;

    @Test
    public void getTasks() {
        //Given
        Task taskOne = new Task("test_one","testing 1");
        Task taskTwo = new Task("test_two","testing 2");
        taskRepository.save(taskOne);
        taskRepository.save(taskTwo);

        //When
        List<TaskDto> resultList = taskController.getTasks();

        //Then
        assertNotNull(resultList);
        assertEquals(2,resultList.size());

        //ClenUp
        taskRepository.delete(taskOne);
        taskRepository.delete(taskTwo);
    }

    @Test
    public void getTask() {
        //Given
        Task taskOne = new Task("test_one","testing 1");
        taskRepository.save(taskOne);

        //When
        Optional<Task> taskResult = taskRepository.findById(taskOne.getId());

        //Then
        assertTrue(taskResult.isPresent());
        assertEquals(taskOne.getTitle(),taskResult.get().getTitle());
        assertEquals(taskOne.getContent(),taskResult.get().getContent());

        //CleanUp
        taskRepository.deleteAll();
    }

    @Test
    public void updateTask() {
        //Given
        Task taskOne = new Task("task1","testing 1");
        taskRepository.save(taskOne);

        //When
        taskController.updateTask(new TaskDto(taskOne.getId(),"task_one","testing"));
        Optional<Task> taskResult = taskRepository.findById(taskOne.getId());

        //Then
        assertTrue(taskResult.isPresent());
        assertEquals("task_one",taskResult.get().getTitle());
        assertEquals("testing",taskResult.get().getContent());

        //CleanUp
        taskRepository.deleteAll();
    }

    @Test(expected = TaskNotFoundException.class)
    public void getTaskException(){
        taskController.getTask(1L);
    }

    @Test
    public void deleteTask(){
        //Given
        Task task = new Task("test 1","testing");
        taskRepository.save(task);

        //When
        taskController.deleteTask(task.getId());
        Optional<Task> taskResult = taskRepository.findById(task.getId());

        //Then
        assertFalse(taskResult.isPresent());
    }

    @Test
    public void createTask(){
        //Given
        TaskDto taskDto = new TaskDto(1L,"test 1","testing");

        //When
        taskController.createTask(taskDto);
        List<Task> resultList = taskRepository.findAll();

        //Then
        assertEquals(taskDto.getTitle(),resultList.get(0).getTitle());
        assertEquals(taskDto.getContent(),resultList.get(0).getContent());

        //CleanUp
        taskRepository.deleteAll();
    }

}