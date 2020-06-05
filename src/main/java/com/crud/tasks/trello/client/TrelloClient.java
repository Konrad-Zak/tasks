package com.crud.tasks.trello.client;

import com.crud.tasks.domain.TrelloBoardDto;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;

    @Value("${trello.app.key}")
    private String trelloAppKey;

    @Value("${trello.app.token}")
    private String trelloToken;

    @Value("${trello.app.userName}")
    private String userName;

    @Autowired
    private RestTemplate restTemplate;

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void setTrelloApiEndpoint(String trelloApiEndpoint) {
        this.trelloApiEndpoint = trelloApiEndpoint;
    }

    public void setTrelloAppKey(String trelloAppKey) {
        this.trelloAppKey = trelloAppKey;
    }

    public void setTrelloToken(String trelloToken) {
        this.trelloToken = trelloToken;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<TrelloBoardDto> getTrelloBoards() {

        Optional<TrelloBoardDto[]> optionalTrelloBoardDtos = Optional.ofNullable(restTemplate.getForObject(getUrl(), TrelloBoardDto[].class));
        return optionalTrelloBoardDtos.map(Arrays::asList).orElseGet(ArrayList::new);
    }

    public URI getUrl(){
        return UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/members/"+ userName +"/boards")
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloToken)
                .queryParam("fields", "name,id").build().encode().toUri();
    }


}
