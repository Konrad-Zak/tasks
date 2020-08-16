package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService service;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    public void getEmptyTasks() throws Exception{
        //Given
        List<Task> tasks = new ArrayList<>();
        List<TaskDto> taskDtos = new ArrayList<>();

        when(service.getAllTasks()).thenReturn(tasks);
        when(taskMapper.mapToTaskDtoList(tasks)).thenReturn(taskDtos);

        //When & Then
        mockMvc.perform(get("/v1/tasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$",hasSize(0)));
    }

    @Test
    public void getTasks() throws Exception{
        //Given
        List<Task> tasks = Arrays.asList(new Task(1L,"Test 1","Testing one"),
                new Task(2L, "Test 2", "Testing two"));
        List<TaskDto> taskDtos = Arrays.asList(new TaskDto(1L,"Test 1","Testing one"),
                new TaskDto(2L, "Test 2", "Testing two"));

        when(service.getAllTasks()).thenReturn(tasks);
        when(taskMapper.mapToTaskDtoList(tasks)).thenReturn(taskDtos);

        //When & Then
        mockMvc.perform(get("/v1/tasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0].id",is(1)))
                .andExpect(jsonPath("$[0].title",is("Test 1")))
                .andExpect(jsonPath("$[0].content",is("Testing one")))
                .andExpect(jsonPath("$[1].id",is(2)))
                .andExpect(jsonPath("$[1].title",is("Test 2")))
                .andExpect(jsonPath("$[1].content",is("Testing two")));
    }


    @Test
    public void getTask() throws Exception{
        //Given
        Optional<Task> optionalTask = Optional.of(new Task(1L, "Test title", "Test content"));
        TaskDto taskDto = new TaskDto(1L,"Test title", "Test content");

        when(service.getTask(1L)).thenReturn(optionalTask);
        when(taskMapper.mapToTaskDto(ArgumentMatchers.any(Task.class))).thenReturn(taskDto);

        //When & Then
        mockMvc.perform(get("/v1/tasks/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.title",is("Test title")))
                .andExpect(jsonPath("$.content",is("Test content")));
    }

    @Test
    public void getTaskExceptionTest() throws Exception {
        //When & Then
        mockMvc.perform(get("/v1/tasks/1000").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteTask() throws Exception{
        //When & Then
        mockMvc.perform(delete("/v1/tasks/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateTask() throws Exception{

        TaskDto taskDto = new TaskDto(10L,"test update","content update");
        Task task = new Task(10L,"test","content");

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        when(taskMapper.mapToTask(ArgumentMatchers.any(TaskDto.class))).thenReturn(task);
        when(service.saveTask(task)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        mockMvc.perform(put("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id",is(10)))
                .andExpect(jsonPath("$.title",is("test update")))
                .andExpect(jsonPath("$.content",is("content update")));
    }

    @Test
    public void createTask() throws Exception{
        //Given
        TaskDto taskDto = new TaskDto(10L,"title","test");
        Task task = new Task(10L,"title","text");

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        when(taskMapper.mapToTask(ArgumentMatchers.any(TaskDto.class))).thenReturn(task);
        when(service.saveTask(task)).thenReturn(task);

        //When & Then
        mockMvc.perform(post("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

}