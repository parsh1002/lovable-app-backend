package com.example.lovable.service;

import com.example.lovable.dto.ChatResponse;
import com.example.lovable.dto.MessageResponse;
import com.example.lovable.entity.Message;
import com.example.lovable.entity.User;
import com.example.lovable.repository.MatchRepository;
import com.example.lovable.repository.MessageRepository;
import com.example.lovable.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public ChatResponse getChat(
            User currentUser,
            UUID receiverId,
            LocalDateTime cursor,
            int size
    ){
        if (size  > 50) {
            throw new RuntimeException("Page size too large");
        }
        User receiver = userRepository
                .findById(receiverId).orElseThrow(() -> new RuntimeException("Invalid Username"));

        Pageable pageable = PageRequest.of(0, size);

        Page<Message> page = messageRepository.findChatMessages(
                currentUser, receiver, cursor, pageable
        );

        List<Message> messages = page.getContent();


        List<MessageResponse> response = messages.stream()
                .map(message -> new MessageResponse(
                        message.getSender().getId(),
                        message.getReceiver().getId(),
                        message.getContent(),
                        message.getTimestamp()
                ))
                .toList();

        LocalDateTime nextCursor = messages.isEmpty()
                ? null : messages.get(messages.size() - 1).getTimestamp();

        return new ChatResponse(response, nextCursor);

    }

}
