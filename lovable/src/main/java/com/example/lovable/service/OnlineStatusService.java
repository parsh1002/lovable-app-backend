package com.example.lovable.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class OnlineStatusService {
    private final Set<String> onlineUsers = ConcurrentHashMap.newKeySet();

    public void userOnline(String name){
        onlineUsers.add(name);
    }

    public void userOffline(String name){
        onlineUsers.remove(name);
    }
    public boolean isOnline(String name){
        return onlineUsers.contains(name);
    }
}
