package com.crud.tasks;

import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Assert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
class TasksApplicationTests {

	private TrelloClient trelloClient = new TrelloClient();

	@ParameterizedTest
	@MethodSource("testArguments")
	public void testGetTrelloBoards(TrelloBoardDto[] tableTrelloBoardDto, int expectedSize){
		//Given
		RestTemplate restTemplateMock = mock(RestTemplate.class);
		trelloClient.setRestTemplate(restTemplateMock);
		trelloClient.setTrelloApiEndpoint("https://api.trello.com/1");
		trelloClient.setTrelloAppKey("2222");
		trelloClient.setTrelloToken("1111");
		trelloClient.setUserName("test");
		when(restTemplateMock.getForObject(trelloClient.getUrl(), TrelloBoardDto[].class)).thenReturn(tableTrelloBoardDto);
		//When
		List<TrelloBoardDto> list = trelloClient.getTrelloBoards();
		//Then
		Assert.assertEquals(expectedSize,list.size());
	}

	private static Stream<Arguments> testArguments(){
		return Stream.of(
				Arguments.of(new TrelloBoardDto[]{new TrelloBoardDto(), new TrelloBoardDto()},2),
				Arguments.of(new TrelloBoardDto[]{new TrelloBoardDto(), new TrelloBoardDto(),new TrelloBoardDto()},3),
				Arguments.of(null,0)
		);
	}

}
