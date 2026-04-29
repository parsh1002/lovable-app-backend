package com.example.lovable.config;

import org.springframework.messaging.Message;
import com.example.lovable.entity.User;
import com.example.lovable.repository.UserRepository;
import com.example.lovable.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class WebSocketAuthInterceptor implements ChannelInterceptor {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel){

        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        if(accessor != null && StompCommand.CONNECT.equals(accessor.getCommand())){
            String authHeader = accessor.getFirstNativeHeader("Authorization");

            if(authHeader != null && authHeader.startsWith("Bearer ")){
                String jwt = authHeader.substring(7);

                String email = jwtService.extractEmail(jwt);

                User user = userRepository.findByEmail(email)
                        .orElseThrow();
                if(jwtService.isTokenValid(jwt, user.getEmail())){
                    accessor.setUser(
                            new UsernamePasswordAuthenticationToken(
                                    user.getId().toString(),
                                    null,
                                    new ArrayList<>()
                            )

                    );
                }
            }
        }
        return message;
    }
}

