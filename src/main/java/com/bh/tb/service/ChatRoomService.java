package com.bh.tb.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bh.tb.config.JasyptConfig;
import com.bh.tb.dto.ChatRoomDTO;

@Service
public class ChatRoomService {

  private Map<String, ChatRoomDTO> chatRoomDTOMap;
  private final JasyptConfig jasyptConfig;

  @Autowired
  public ChatRoomService(JasyptConfig jasyptConfig) {
    this.jasyptConfig = jasyptConfig;
  }
  
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
    ChatRoomDTO room = ChatRoomDTO.create(name, jasyptConfig.getEncryptedPlainText(password));
    chatRoomDTOMap.put(room.getRoomId(), room);
    return room;
  }

  public boolean checkPassword(String roomPassword, String enteredPassword) {
    String decryptedPassword = jasyptConfig.getDecrpytedPlainText(roomPassword);
    if(decryptedPassword.equals(enteredPassword)) {
      return true;
    }
    return false;
  }

  public void removeRoom(String roomId) {
    chatRoomDTOMap.remove(roomId);
  }

  
}
