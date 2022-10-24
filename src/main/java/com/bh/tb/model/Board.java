package com.bh.tb.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class Board {
	private int id;
	private String title;
	private String content;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private int viewCount;
	private String imagePath;
	private List<ContentsFile> contentsFiles;

	@JsonIgnore
	public String getFilesStringValue() {
		StringBuilder sb = new StringBuilder();
		if(this.contentsFiles != null && !this.contentsFiles.isEmpty()) {
			int index = 0;
			for(ContentsFile contentsFiles : this.contentsFiles) {
				if(index++ != 0) {
					sb.append(" // ");
				}
				sb.append(contentsFiles.getName());
			}
		} else {
			return "";
		}
		return sb.toString();
	}
	
	@JsonIgnore
	public String getViewImageName() {
		if(this.imagePath != null) {
			String[] splitImageName = this.imagePath.split("/");
			return splitImageName[splitImageName.length - 1];
		} else {
			return "";
		}
	}
}
