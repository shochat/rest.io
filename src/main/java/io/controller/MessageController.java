package io.controller;

import io.config.security.JwtUtils;
import io.config.security.UserDetailsImpl;
import io.model.message.Message;
import io.model.message.MessageRequest;
import io.service.MessageService;
import io.service.UserDetailsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    MessageService messageService;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    UserDetailsService userDetailsService;

    @PostMapping("/post")
    public ResponseEntity<?> postMessage(@Valid @RequestBody MessageRequest messageRequest, @CookieValue(name = "mb-cookie")String mbCookie) {
        String userNameFromJwtToken = jwtUtils.getUserNameFromJwtToken(mbCookie);
        Long userId = userDetailsService.loadUserByUsername(userNameFromJwtToken).getId();
        Message message = new Message();
        message.setText(messageRequest.getText());
        message.setVoteRate(0);
        message.setAutherId(userId);
        messageService.save(message);
        return ResponseEntity.ok().body("Message successfully saved: " + messageRequest.getText());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMessage(@PathVariable Long id) {
        return messageService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound()
                        .build());
    }

    @GetMapping("/getAllForUser/{userName}")
    public ResponseEntity<?> getAllMessages(@PathVariable String userName) {
        UserDetailsImpl userDetails = userDetailsService.loadUserByUsername(userName);
        Long userId = userDetails.getId();
        return messageService.getAllByAuther(userId).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound()
                        .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMessage(@PathVariable Long id) {
        return ResponseEntity.ok(messageService.delete(id));
    }

    @PutMapping("/{rate}")
    public String rateMessage(@PathVariable boolean rate) {
        return "rate message";
    }
}
