package com.bh.tb.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bh.tb.model.ContentsFile;
import com.bh.tb.service.ContentsFileRestService;

@Controller
@RequestMapping("/contentsFile")
public class ContentsFileController {
  private final ContentsFileRestService contentsFileRestService;

  public ContentsFileController(ContentsFileRestService contentsFileRestService) {
    this.contentsFileRestService = contentsFileRestService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<byte[]> download(@PathVariable int id) {
    return contentsFileRestService.download(id);
  }

  @PostMapping
  public ResponseEntity<ContentsFile> upload(@RequestParam("file") MultipartFile multipartFile) throws IOException {
    ContentsFile contentsFile = contentsFileRestService.upload(multipartFile);
    return new ResponseEntity<>(contentsFile, HttpStatus.OK);
  }

}
