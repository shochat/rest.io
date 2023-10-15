package io.service;

import io.config.security.UserDetailsImpl;
import io.model.message.Message;
import io.model.message.MessageRateRequest;
import io.model.message.MessageRequest;
import io.repository.MessageRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    UserService userService;
    public Optional<Message> findById(Long id) {
        return messageRepository.findById(id);
    }

    @Override
    public Optional<List<Message>> getAllByAuther(String userName) {
        UserDetailsImpl userDetails = userService.loadUserByUsername(userName);
        Long userId = userDetails.getId();
        return messageRepository.findAllByAutherId(userId);
    }

    @Override
public Message save(Message message) {
        return messageRepository.save(message);
    }

    @Transactional
    public String delete(Long messageId) {
        int deletedRecordsCount = messageRepository.deleteMessageById(messageId);
        if (deletedRecordsCount != 1 ) {
            return String.format("%d messages deleted", deletedRecordsCount);
        }
        return String.format("Message with id %d successfully deleted", messageId);
    }

    @Transactional
    public Optional<Message> updateMessageRate(MessageRateRequest messageRateRequest) {
        Optional<Message> optionalMessage = messageRepository.findById(messageRateRequest.getMessageId());
        optionalMessage.ifPresent(message -> {
            int newRate = messageRateRequest.isRate() ? message.getVoteRate() + 1 : message.getVoteRate() - 1;
            messageRepository.updateVoteRate(newRate, messageRateRequest.getMessageId().intValue());
        });
        return messageRepository.findById(messageRateRequest.getMessageId());
    }

    @Override
    public Message postMessage(MessageRequest messageRequest, String mbCookie) {
        Long userId = userService.extractUserIdFromToken(mbCookie);
        Message message = new Message();
        message.setText(messageRequest.getText());
        message.setVoteRate(0);
        message.setAutherId(userId);
        return this.save(message);
    }
}
