package com.crud.tasks.service;

import com.crud.tasks.domain.*;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTest {

    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private TrelloClient trelloClient;


    @Test
    public void fetchTrelloBoards() {
        //Given
        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        trelloBoardDtos.add(new TrelloBoardDto("test","1", new ArrayList<>()));
        trelloBoardDtos.add(new TrelloBoardDto("test 2","2", new ArrayList<>()));
        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardDtos);

        //When
        List<TrelloBoardDto> resultList = trelloService.fetchTrelloBoards();

        //Then
        assertEquals(2,resultList.size());
        assertEquals("test",resultList.get(0).getName());
        assertEquals("1",resultList.get(0).getId());
        assertEquals("test 2",resultList.get(1).getName());
        assertEquals("2",resultList.get(1).getId());
    }

    @Test
    public void createdNullTrelloCard() {
        //When
        CreatedTrelloCardDto resultCard = trelloService.createdTrelloCard(null);

        //Then
        assertNull(resultCard);
    }
}