package com.crud.tasks.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class CreatedTrelloCardDtoTest {

    @Test
    public void getBadges() {
        //Given
        Badges badges = new Badges(2,new AttachmentsByType(new Trello(1,2)));

        //When
        CreatedTrelloCardDto cardDto  = new CreatedTrelloCardDto("1","test",
                "https://my.com",badges);

        //Then
        assertEquals(2,cardDto.getBadges().getVotes());
        assertEquals(1,cardDto.getBadges().getAttachmentsByType().getTrello().getBoard());
        assertEquals(2,cardDto.getBadges().getAttachmentsByType().getTrello().getCard());
    }
}