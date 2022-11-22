package com.bh.tb.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

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

  public Board get(int id) {
    return restTemplate.getForObject(BOARD_API_SERVER + "/" + id, Board.class);
  }

  //create + edit
  public Board create(Board board, List<MultipartFile> attachedFiles, MultipartFile mainImageFile, List<String> contentFileNames) throws IOException {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
    MultiValueMap<String, Object> creationMap = new LinkedMultiValueMap<>();
    
    if(!attachedFiles.get(0).isEmpty() ) {
      ByteArrayResource fileResource = new ByteArrayResource(attachedFiles.get(0).getBytes());

      for(MultipartFile attachedFile : attachedFiles) {
        fileResource = new ByteArrayResource(attachedFile.getBytes()) {
          @Override
          public String getFilename() {
            return attachedFile.getOriginalFilename();
          }
        };
        creationMap.add("attachedFiles", fileResource);
      }
    }

    if(!mainImageFile.isEmpty()) {
      ByteArrayResource fileResource = new ByteArrayResource(mainImageFile.getBytes()) {
        @Override
        public String getFilename() {
          return mainImageFile.getOriginalFilename();
        }};
      creationMap.add("mainImageFile", fileResource);
    }
    
    if(!contentFileNames.isEmpty()) {
      creationMap.add("contentFileNames", contentFileNames);
    }

    creationMap.add("board", board);
    HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(creationMap, httpHeaders);

    return restTemplate.postForObject(BOARD_API_SERVER, httpEntity, Board.class);
  }

  public void delete(int id, List<String> fileUrls) {
    HttpEntity<List<String>> httpEntity = new HttpEntity<>(fileUrls);
    restTemplate.exchange(BOARD_API_SERVER+"/"+id, HttpMethod.DELETE, httpEntity, String.class);
  }

}
