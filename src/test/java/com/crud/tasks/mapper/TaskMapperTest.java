package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TaskMapperTest {

    @Autowired
    private TaskMapper taskMapper;

    @Test
    public void mapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L,"title one","test");
        //When
        Task taskResult = taskMapper.mapToTask(taskDto);
        //Then
        assertNotNull(taskResult);
        assertEquals(taskDto.getTitle(),taskResult.getTitle());
    }

    @Test
    public void mapToTaskDto() {
        //Given
        Task task = new Task(1L,"title two","test");
        //When
        TaskDto taskDtoResult = taskMapper.mapToTaskDto(task);
        //Then
        assertNotNull(taskDtoResult);
        assertEquals(task.getTitle(),taskDtoResult.getTitle());
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
        assertEquals(tasks.get(1).getTitle(),resultList.get(1).getTitle());
    }
}