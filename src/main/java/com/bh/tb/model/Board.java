package com.bh.tb.model;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class Board {
	private int id;
	private String title;
	private String content;
	private String autor;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private int viewCount;
	private String imagePath;
	private List<File> files;

	public String getFilesStringValue() {
		StringBuilder sb = new StringBuilder();
		if(this.files != null && !this.files.isEmpty()) {
			int index = 0;
			for(File file : this.files) {
				if(index++ != 0) {
					sb.append(" // ");
				}
				sb.append(file.getName());
			}
		} else {
			return "";
		}
		return sb.toString();
	}
	
	public String getViewImageName() {
		if(this.imagePath != null) {
			String[] splitImageName = this.imagePath.split("/");
			return splitImageName[splitImageName.length - 1];
		} else {
			return "";
		}
	}
}
