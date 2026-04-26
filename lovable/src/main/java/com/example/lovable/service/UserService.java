package com.example.lovable.service;


import com.example.lovable.entity.Role;
import com.example.lovable.entity.User;
import com.example.lovable.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtService jwtService;

    public User register(User user){
        if(userRepository.existsByEmail(user.getEmail())){
            throw new RuntimeException("Email Already Exists");
        }
        if(userRepository.existsByUsername(user.getUsername())){
            throw new RuntimeException("User Name Already Exists");
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        user.setRole(Role.USER);

        return userRepository.save(user);

    }
    public String Login(String email, String password){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email not in existence."));

//        Check password
        if(!bCryptPasswordEncoder.matches(password, user.getPassword())){
            throw new RuntimeException("Invalid Password.");
        }
        return jwtService.generateToken(user.getEmail());

    }

}
