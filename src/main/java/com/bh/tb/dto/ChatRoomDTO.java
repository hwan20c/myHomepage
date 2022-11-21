package com.bh.tb.dto;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.web.socket.WebSocketSession;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRoomDTO {

  private String roomId;
  private String name;
  private String password;
  private Set<WebSocketSession> sessions = new HashSet<>();
  
  public static ChatRoomDTO create(String name) {
    ChatRoomDTO room = new ChatRoomDTO();
    room.roomId = UUID.randomUUID().toString();
    room.name = name;
    return room;
  }

  public static ChatRoomDTO create(String name, String password) {
    ChatRoomDTO room = new ChatRoomDTO();
    room.roomId = UUID.randomUUID().toString();
    room.name = name;
    room.password = password;
    return room;
  }
  
}
