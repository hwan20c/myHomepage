package com.bh.tb.model;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class Board {
    private int id;
    private String title;
    private String content;
    private String autor;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private int viewCount;
    private String imagePath;
}
