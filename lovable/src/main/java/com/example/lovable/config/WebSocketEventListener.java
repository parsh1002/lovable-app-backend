package com.example.lovable.config;

import com.example.lovable.repository.UserRepository;
import com.example.lovable.service.OnlineStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.socket.messaging.SessionConnectEvent;


@Configuration
@RequiredArgsConstructor
public class WebSocketEventListener {

    private final OnlineStatusService onlineStatusService;
    private final UserRepository userRepository;

    @EventListener
    public void handleConnect(SessionConnectEvent event){
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());

        if(accessor.getUser() != null){
            onlineStatusService.userOnline(accessor.getUser().getName());
        }
    }

    @EventListener
    public void handleDisconnect(SessionConnectEvent event){
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());

        if(accessor.getUser() != null){
            onlineStatusService.userOffline(accessor.getUser().getName());
        }
    }
}
