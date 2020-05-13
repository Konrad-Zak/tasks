package com.crud.tasks;

import com.crud.tasks.domain.Task;
import com.crud.tasks.service.DbService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
		Long id = 1L;
		//When
		Task task = dbService.getTaskById(id);
		//Then
		Assert.assertEquals("test",task.getTitle());
		Assert.assertEquals("test1",task.getContent());
	}

}
