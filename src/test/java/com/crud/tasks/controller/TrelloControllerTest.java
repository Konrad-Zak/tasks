package com.crud.tasks.controller;

import com.crud.tasks.domain.*;
import com.crud.tasks.trello.facade.TrelloFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloControllerTest {

    @InjectMocks
    TrelloController trelloController;

    @Mock
    TrelloFacade trelloFacade;

    @Test
    public void getTrelloBoards() {
        //Given
        List<TrelloListDto> trelloLists= Arrays.asList(new TrelloListDto("1","to do",true),
                new TrelloListDto("2","soon",false));
        List<TrelloBoardDto> trelloBoardList = new ArrayList<>();
        trelloBoardList.add(new TrelloBoardDto("April","1",trelloLists));
        when(trelloFacade.fetchTrelloBoards()).thenReturn(trelloBoardList);

        //When
        List<TrelloBoardDto> resultList = trelloController.getTrelloBoards();

        //Then
        assertEquals(1,resultList.size());
        assertEquals(2,resultList.get(0).getLists().size());
        assertEquals("April",resultList.get(0).getName());
        assertEquals("to do",resultList.get(0).getLists().get(0).getName());
        assertTrue(resultList.get(0).getLists().get(0).isClosed());
        assertEquals("soon",resultList.get(0).getLists().get(1).getName());
        assertFalse(resultList.get(0).getLists().get(1).isClosed());
    }

    @Test
    public void createdTrelloCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("to do","text","last","1");
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("1","to do",
                "http://test.com");
        when(trelloFacade.createCard(trelloCardDto)).thenReturn(createdTrelloCardDto);

        //When
        CreatedTrelloCardDto cardResult = trelloController.createdTrelloCard(trelloCardDto);

        //Then
        assertEquals("1",cardResult.getId());
        assertEquals("to do",cardResult.getName());
        assertEquals("http://test.com",cardResult.getShortUrl());
        assertNull(cardResult.getBadges());
    }
}