package com.bh.tb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BoardRestService {
  // private final RestTemplate restTemplate;

  public String TB_API_SERVER = "";

  @Autowired
  public BoardRestService(
    // RestTemplate restTemplate,
                          @Value("{app.api.url}") String tbApiUrl,
                          @Value("{app.api.port}") String tbApiPort) {
        // this.restTemplate = restTemplate;
        this.TB_API_SERVER = tbApiUrl + ":" + tbApiPort + "boardurl";
      }


}
