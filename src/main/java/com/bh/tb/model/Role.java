package com.bh.tb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "roles")
public class Role {

  @Id
	private int id;
	@Column
	private String role;
	
	public Role() { }
	public Role(int id, String role) {
		this.id = id;
		this.role = role;
	}
  
}
