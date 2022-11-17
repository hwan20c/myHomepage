package com.bh.tb.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.bh.tb.dto.ChatMessageDTO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class StompController {
  private final SimpMessagingTemplate template; //특정 Broker로 메세지를 전달

  @MessageMapping(value = "/chat/enter")
  public void enter(ChatMessageDTO message){
      message.setMessage(message.getWriter() + "님이 채팅방에 참여하였습니다.");
      template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
  }

  @MessageMapping(value = "/chat/message")
  public void message(ChatMessageDTO message){
      template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
  }
}
