package com.bh.tb.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardSearchRequest {
  private int page = 0;
  private int size = 9;
  private int type = 0;
  private String searchKey;
  private String searchValue;
}

