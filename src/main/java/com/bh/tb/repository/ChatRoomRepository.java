package com.bh.tb.repository;

import java.util.*;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.bh.tb.config.JasyptConfig;
import com.bh.tb.dto.ChatRoomDTO;

@Repository
public class ChatRoomRepository {
  
  private Map<String, ChatRoomDTO> chatRoomDTOMap;
  private JasyptConfig jasyptConfig;

  @PostConstruct
  private void init() {
    chatRoomDTOMap = new LinkedHashMap<>();
  }

  public List<ChatRoomDTO> findAllRooms() {

    List<ChatRoomDTO> result = new ArrayList<>(chatRoomDTOMap.values());
    Collections.reverse(result);

    return result;
  }

  public ChatRoomDTO findRoomById(String id) {
    return chatRoomDTOMap.get(id);
  }

  public ChatRoomDTO createChatRoomDTO(String name) {
    ChatRoomDTO room = ChatRoomDTO.create(name);
    chatRoomDTOMap.put(room.getRoomId(), room);

    return room;
  }

  
  public ChatRoomDTO createChatRoomDTO(String name, String password) {
    ChatRoomDTO room = ChatRoomDTO.create(name, jasyptConfig.getDecrpytedPlainText(password));
    chatRoomDTOMap.put(room.getRoomId(), room);
    
    return room;
  }

}
