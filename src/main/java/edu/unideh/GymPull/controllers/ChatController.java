package edu.unideh.GymPull.controllers;

import edu.unideh.GymPull.entity.Chat;
import edu.unideh.GymPull.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping("/create")
    public Chat createChat(@RequestBody Chat chat) {
        return chatService.createChat(chat);
    }

    @GetMapping("/all")
    public List<Chat> getAllChats() {
        return chatService.getAllChats();
    }

    @GetMapping("/{id}")
    public Chat getChatById(@PathVariable Integer id) {
        return chatService.getChatById(id).orElse(null);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteChat(@PathVariable Integer id) {
        chatService.deleteChat(id);
    }

    @PutMapping("/update/{id}")
    public Chat updateChat(@PathVariable Integer id, @RequestBody Chat chatDetails) {
        return chatService.updateChat(id, chatDetails);
    }
}
