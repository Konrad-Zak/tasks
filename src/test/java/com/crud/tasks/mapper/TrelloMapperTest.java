package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;



public class TrelloMapperTest {

    private TrelloMapper trelloMapper = new TrelloMapper();

    @Test
    public void mapToBoard() {
        //Given
        List<TrelloListDto> trelloListDtos = Arrays.asList(
                new TrelloListDto("1","one",false),
                new TrelloListDto("2","two",true)
        );
        List<TrelloBoardDto> trelloBoardDtos = Collections.singletonList(new TrelloBoardDto("test",
                "1", trelloListDtos));
        //When
        List<TrelloBoard> resultList = trelloMapper.mapToBoard(trelloBoardDtos);
        //Then
        assertEquals(trelloBoardDtos.size(),resultList.size());
        assertEquals(trelloBoardDtos.get(0).getLists().size(),resultList.get(0).getLists().size());
    }

    @Test
    public void mapToBoardsDto() {
        List<TrelloList> trelloList = Arrays.asList(
                new TrelloList("1","one",false),
                new TrelloList("2","two",false)
        );
        List<TrelloBoard> trelloBoards = Collections.singletonList(new TrelloBoard("test2",
                "1", trelloList));
        //When
        List<TrelloBoardDto> resultList = trelloMapper.mapToBoardsDto(trelloBoards);
        //Then
        assertEquals(trelloBoards.size(),resultList.size());
        assertEquals(trelloBoards.get(0).getLists().size(),resultList.get(0).getLists().size());
    }

    @Test
    public void mapToList() {
        //Given
        List<TrelloListDto> trelloListDtos = Arrays.asList(
                new TrelloListDto("1","one",false),
                new TrelloListDto("2","two",true)
        );
        //When
        List<TrelloList> resultList = trelloMapper.mapToList(trelloListDtos);
        //Then
        assertEquals(trelloListDtos.size(),resultList.size());
        assertEquals(trelloListDtos.get(1).getName(),resultList.get(1).getName());
    }

    @Test
    public void mapToListDto() {
        //Given
        List<TrelloList> trelloList = Arrays.asList(
                new TrelloList("1","one",false),
                new TrelloList("2","two",false)
        );
        //When
        List<TrelloListDto> resultList = trelloMapper.mapToListDto(trelloList);
        //Then
        assertEquals(trelloList.size(),resultList.size());
        assertEquals(trelloList.get(1).getName(),resultList.get(1).getName());
    }

    @Test
    public void mapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("one","first card","last","10");
        //When
        TrelloCardDto resultCard = trelloMapper.mapToCardDto(trelloCard);
        //Then
        assertNotNull(resultCard);
        assertEquals(trelloCard.getPos(),resultCard.getPos());
    }

    @Test
    public void mapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("two","second card","first","11");
        //When
        TrelloCard resultCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        assertNotNull(resultCard);
        assertEquals(trelloCardDto.getPos(),resultCard.getPos());
    }
}