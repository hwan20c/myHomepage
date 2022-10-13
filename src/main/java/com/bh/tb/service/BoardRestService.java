package com.bh.tb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bh.tb.model.Board;
import com.bh.tb.util.PageableResponse;

@Service
public class BoardRestService {
  private final RestTemplate restTemplate;

  public String BOARD_API_SERVER = "";

  @Autowired
  public BoardRestService(RestTemplate restTemplate,
                          @Value("${app.api.url}") String tbApiUrl,
                          @Value("${app.api.port}") String tbApiPort) {
      this.restTemplate = restTemplate;
      this.BOARD_API_SERVER = tbApiUrl + ":" + tbApiPort + "/api/boards";
    }

  public PageableResponse<Board> listwithPageable(String search) {
    ResponseEntity<PageableResponse<Board>> response
     = restTemplate.exchange(BOARD_API_SERVER + search, HttpMethod.GET, null, new ParameterizedTypeReference<PageableResponse<Board>>() {});
    return response.getBody();
  }

  public Board detailPage(int id) {
    ResponseEntity<Board> response 
     = restTemplate.exchange(BOARD_API_SERVER + "/" + id, HttpMethod.GET, null, new ParameterizedTypeReference<Board>() {});
     return response.getBody();
  }

}
