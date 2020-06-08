package com.crud.tasks.trello.client;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class TrelloClient {

    private String trelloApiEndpoint;
    private String trelloAppKey;
    private String trelloToken;
    private String userName;
    private RestTemplate restTemplate;

    public TrelloClient(@Value("${trello.api.endpoint.prod}") String trelloApiEndpoint,
                        @Value("${trello.app.key}") String trelloAppKey,
                        @Value("${trello.app.token}") String trelloToken,
                        @Value("${trello.app.userName}") String userName,
                        RestTemplate restTemplate) {
        this.trelloApiEndpoint = trelloApiEndpoint;
        this.trelloAppKey = trelloAppKey;
        this.trelloToken = trelloToken;
        this.userName = userName;
        this.restTemplate = restTemplate;
    }

    public List<TrelloBoardDto> getTrelloBoards() {

        Optional<TrelloBoardDto[]> optionalTrelloBoardDtos = Optional.ofNullable(restTemplate.getForObject(getUrl(), TrelloBoardDto[].class));
        return optionalTrelloBoardDtos.map(Arrays::asList).orElseGet(ArrayList::new);
    }

    public URI getUrl(){
        return UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/members/"+ userName +"/boards")
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloToken)
                .queryParam("fields","name,id")
                .queryParam("lists", "all").build().encode().toUri();
    }

    public CreatedTrelloCard createNewCard(TrelloCardDto trelloCardDto){
        URI url = UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/cards")
                .queryParam("key",trelloAppKey)
                .queryParam("token",trelloToken)
                .queryParam("name",trelloCardDto.getName())
                .queryParam("desc",trelloCardDto.getDescription())
                .queryParam("pos",trelloCardDto.getPos())
                .queryParam("idList",trelloCardDto.getListId()).build().encode().toUri();
        return restTemplate.postForObject(url,null,CreatedTrelloCard.class);
    }


}
