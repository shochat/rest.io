package io.service;

import io.model.message.Message;
import io.model.message.MessageRateRequest;
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
    public Optional<Message> findById(Long id) {
        return messageRepository.findById(id);
    }

    @Override
    public Optional<List<Message>> getAllByAuther(Long autherId) {
        return messageRepository.findAllByAutherId(autherId);
    }

    @Override
    public void save(Message message) {
        messageRepository.save(message);
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
}
