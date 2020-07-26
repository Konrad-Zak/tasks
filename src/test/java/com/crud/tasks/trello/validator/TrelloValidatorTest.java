package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TrelloValidatorTest {

    TrelloValidator trelloValidator = new TrelloValidator();

    @Test
    public void validateTrelloBoards() {
        //Given
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("1","to do",new ArrayList<>()));
        trelloBoards.add(new TrelloBoard("2","test",new ArrayList<>()));
        trelloBoards.add(new TrelloBoard("3","new unit",new ArrayList<>()));

        //When
        List<TrelloBoard> resultList = trelloValidator.validateTrelloBoards(trelloBoards);

        //Then
        assertEquals(2,resultList.size());
        assertEquals(trelloBoards.get(0).getName(),resultList.get(0).getName());
        assertEquals(trelloBoards.get(0).getId(),resultList.get(0).getId());
        assertEquals(trelloBoards.get(2).getId(),resultList.get(1).getId());
        assertEquals(trelloBoards.get(2).getName(),resultList.get(1).getName());
    }
}