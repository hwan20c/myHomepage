package com.bh.tb.service;

import java.io.IOException;

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

import com.bh.tb.model.ContentsFile;

@Service
public class ContentsFileRestService {
  private final RestTemplate restTemplate;
  public String CONTENTSFILE_API_SERVER = "";

  @Autowired
  public ContentsFileRestService(RestTemplate restTemplate,
                          @Value("${app.api.url}") String tbApiUrl,
                          @Value("${app.api.port}") String tbApiPort) {
      this.restTemplate = restTemplate;
      this.CONTENTSFILE_API_SERVER = tbApiUrl + ":" + tbApiPort + "/api/contentsFiles";
    }

  public ResponseEntity<byte[]> download(int id) {
    return restTemplate.exchange(CONTENTSFILE_API_SERVER + "/" + id, HttpMethod.GET, null, new ParameterizedTypeReference<byte[]>() {});
  }

  public ContentsFile upload(MultipartFile multipartFile) throws IOException {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
    MultiValueMap<String, Object> creationMap = new LinkedMultiValueMap<>();
    if(!multipartFile.isEmpty()) {
      ByteArrayResource fileResource = new ByteArrayResource(multipartFile.getBytes()) {
        @Override
        public String getFilename() {
          return multipartFile.getOriginalFilename();
        }};
      creationMap.add("file", fileResource);
    }
    HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(creationMap, httpHeaders);
    return restTemplate.postForObject(CONTENTSFILE_API_SERVER, httpEntity, ContentsFile.class);
  }
  
}
