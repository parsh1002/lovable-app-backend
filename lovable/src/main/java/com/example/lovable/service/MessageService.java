package com.example.lovable.service;

import com.example.lovable.entity.Message;
import com.example.lovable.entity.User;
import com.example.lovable.repository.MatchRepository;
import com.example.lovable.repository.MessageRepository;
import com.example.lovable.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MatchRepository matchRepository;
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    public String sendMessage(User currentUser, UUID receiverId, String content) {

        User receiver = userRepository.findById(receiverId)
                .orElseThrow(() -> new RuntimeException("Invalid User ID."));

        boolean isMatched = matchRepository
                .findByUser1OrUser2(currentUser, currentUser)
                .stream()
                .anyMatch(matches ->
                        matches.getUser1().equals(receiver) || matches.getUser2().equals(receiver)
                );

        if (!isMatched) return "Only the matches can message each other";

        Message message = new Message();
        message.setSender(currentUser);
        message.setReceiver(receiver);
        message.setContent(content);
        messageRepository.save(message);

        return "Message sent";

    }

    public List<Message> getChat(User currentUser, UUID receiverId){
        User receiver = userRepository
                .findById(receiverId).orElseThrow(() -> new RuntimeException("Invalid Username"));

        return messageRepository
                .findBySenderAndReceiverOrReceiverAndSenderOrderByTimestampAsc(
                        currentUser, receiver,
                        currentUser, receiver
                );

    }

}
