package com.bh.tb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bh.tb.service.ChatRoomService;

@Controller
@RequestMapping(value = "/chat")
public class ChatRoomController {

  private final ChatRoomService chatRoomService;

  public ChatRoomController(ChatRoomService chatRoomService) {
    this.chatRoomService = chatRoomService;
  }

  //채팅방 목록 조회
  @GetMapping(value = "/rooms")
  public ModelAndView rooms(){
    System.out.println("# All Chat Rooms");
    ModelAndView mv = new ModelAndView("chat/rooms");
    mv.addObject("list", chatRoomService.findAllRooms());
    return mv;
  }

  //채팅방 개설
  @PostMapping(value = "/room")
  public String create(@RequestParam String roomName, @RequestParam String roomPassword, RedirectAttributes rttr){
    System.out.println("# Create Chat Room , name: " + roomName);
    if(roomPassword.equals("")) {
      rttr.addFlashAttribute("roomName", chatRoomService.createChatRoomDTO(roomName));
    } else {
      rttr.addFlashAttribute("roomName", chatRoomService.createChatRoomDTO(roomName, roomPassword));
    }
    
    return "redirect:/chat/rooms";
  }

  //채팅방 진입
  @GetMapping("/room")
  public void getRoom(String roomId, Model model){
    System.out.println("# get Chat Room, roomID : " + roomId);
    model.addAttribute("room", chatRoomService.findRoomById(roomId));
  }
  
}
