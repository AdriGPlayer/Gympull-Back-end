package edu.unideh.GymPull.service;

import edu.unideh.GymPull.entity.Chat;
import edu.unideh.GymPull.repositories.ChatRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    public Chat createChat(Chat chat) {
        return chatRepository.save(chat);
    }

    public List<Chat> getAllChats() {
        return chatRepository.findAll();
    }

    public Optional<Chat> getChatById(Integer id) {
        return chatRepository.findById(id);
    }

    public void deleteChat(Integer id) {
        chatRepository.deleteById(id);
    }

    public Chat updateChat(Integer id, Chat chatDetails) {
        return chatRepository.findById(id).map(chat -> {
            chat.setFecha(chatDetails.getFecha());
            chat.setIdcliente(chatDetails.getIdcliente());
            chat.setTipo(chatDetails.getTipo());
            chat.setMensaje(chatDetails.getMensaje());
            return chatRepository.save(chat);
        }).orElse(null);
    }
}
