package com.bh.tb.model;

import lombok.Data;

@Data
public class ContentsFile {
  private int id;
  private String name;
  private String bucketName;
  private String path;
  private int noticeId;
}
