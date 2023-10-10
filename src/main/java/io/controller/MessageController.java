package io.controller;

import io.config.security.JwtUtils;
import io.model.message.Message;
import io.model.message.MessageRequest;
import io.model.message.MessageResponse;
import io.model.message.User;
import io.repository.MessageRepository;
import io.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/post")
    public ResponseEntity<?> postMessage(@Valid @RequestBody MessageRequest messageRequest, @CookieValue(name = "mb-cookie")String mbCookie) {
        String userNameFromJwtToken = jwtUtils.getUserNameFromJwtToken(mbCookie);
        Long userId = userRepository.findByUsername(userNameFromJwtToken).map(User::getId).orElseThrow();

        Message message = new Message();
        message.setText(messageRequest.getText());
        message.setVoteRate(0);
        message.setAutherId(userId);
        messageRepository.save(message);
        return ResponseEntity.ok().body("Message successfully saved: " + messageRequest.getText());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMessage(@PathVariable Long id) {
        return messageRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound()
                        .build());
    }

    @GetMapping("/getAllForUser")
    public String getAllMessages() {
        return "post message";
    }

    @DeleteMapping("/{id}")
    public String deleteMessage(String id) {
        return "post message";
    }

    @PutMapping("/{rate}")
    public String rateMessage(boolean rate) {
        return "rate message";
    }
}
