package io.service;

import io.model.message.Message;
import io.model.message.MessageRateRequest;
import io.model.message.MessageRequest;

import java.util.List;
import java.util.Optional;

public interface MessageService {
    Optional<Message> findById(Long id);
    Optional<List<Message>> getAllByAuther(String userName);
    Message save(Message message);

    String delete(Long messageId);

    Optional<Message> updateMessageRate(MessageRateRequest messageRateRequest);

    Message postMessage(MessageRequest messageRequest, String mbCookie);
}
