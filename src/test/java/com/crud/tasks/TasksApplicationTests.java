package com.crud.tasks;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.DbService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class TasksApplicationTests {

	@Autowired
	DbService dbService;

	@Test
	void contextLoads() {
	}

	@Test
	public void testGetTaskById(){
		//Given
		String title = "test_123";
		String content = "test 123";
		Long id = 1L;
		TaskRepository taskRepository = mock(TaskRepository.class);
		Optional<Task> optionalTask = Optional.of(new Task(id, title, content));
		when(taskRepository.findById(id)).thenReturn(optionalTask);
		dbService.setRepository(taskRepository);
		//When
		Task task = dbService.getTaskById(id);
		//Then
		Assert.assertEquals(title,task.getTitle());
		Assert.assertEquals(content,task.getContent());
	}

}
