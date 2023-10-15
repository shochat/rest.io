package io.controller;

import io.model.message.Message;
import io.model.message.MessageRateRequest;
import io.model.message.MessageRequest;
import io.service.MessageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    MessageService messageService;

    @PostMapping("/post")
    public ResponseEntity<?> postMessage(@Valid @RequestBody MessageRequest messageRequest,
                                         @CookieValue(name = "mb-cookie")String mbCookie) {
        Message newMessage = messageService.postMessage(messageRequest, mbCookie);
        if (newMessage != null) {
            return ResponseEntity.ok(newMessage);
        }
        return new ResponseEntity<>("Failed to post message", HttpStatusCode.valueOf(500));
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
        return messageService.getAllByAuther(userName).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound()
                        .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMessage(@PathVariable Long id) {
        return ResponseEntity.ok(messageService.delete(id));
    }

    @PutMapping("/rate")
    public ResponseEntity<?> rateMessage(@RequestBody MessageRateRequest messageRateRequest) {
        return messageService.updateMessageRate(messageRateRequest)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(500).build());
    }
}
