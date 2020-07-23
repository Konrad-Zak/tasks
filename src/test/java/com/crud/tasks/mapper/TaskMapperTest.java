package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TaskMapperTest {

    private TaskMapper taskMapper = new TaskMapper();

    @Test
    public void mapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L,"title one","test");
        //When
        Task taskResult = taskMapper.mapToTask(taskDto);
        //Then
        assertNotNull(taskResult);
        assertEquals(taskDto.getId(),taskResult.getId());
        assertEquals(taskDto.getTitle(),taskResult.getTitle());
        assertEquals(taskDto.getContent(),taskResult.getContent());
    }

    @Test
    public void mapToTaskDto() {
        //Given
        Task task = new Task(1L,"title two","test");
        //When
        TaskDto taskDtoResult = taskMapper.mapToTaskDto(task);
        //Then
        assertNotNull(taskDtoResult);
        assertEquals(task.getId(),taskDtoResult.getId());
        assertEquals(task.getTitle(),taskDtoResult.getTitle());
        assertEquals(task.getContent(),taskDtoResult.getContent());
    }

    @Test
    public void mapToTaskDtoList() {
        //Given
        List<Task> tasks = Arrays.asList(
                new Task(1L,"title one","test"),
                new Task(2L,"title two","test 2"));
        //When
        List<TaskDto> resultList = taskMapper.mapToTaskDtoList(tasks);
        //Then
        assertEquals(tasks.size(),resultList.size());
        assertEquals(tasks.get(0).getId(),tasks.get(0).getId());
        assertEquals(tasks.get(0).getTitle(),resultList.get(0).getTitle());
        assertEquals(tasks.get(0).getContent(),resultList.get(0).getContent());
        assertEquals(tasks.get(1).getId(),resultList.get(1).getId());
        assertEquals(tasks.get(1).getTitle(),resultList.get(1).getTitle());
        assertEquals(tasks.get(1).getContent(),resultList.get(1).getContent());
    }
}