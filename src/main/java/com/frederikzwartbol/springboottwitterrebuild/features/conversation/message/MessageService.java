package com.frederikzwartbol.springboottwitterrebuild.features.conversation.message;

import com.frederikzwartbol.springboottwitterrebuild.exceptions.exceptions.GenericNotFoundException;
import com.frederikzwartbol.springboottwitterrebuild.features.conversation.models.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

//    public Message saveMessageFromRequest (MessageRequest request) {
//
//        messageRepository.save()
//    }
//
//    public Slice<Message> findMessagesOfUserId (Long receiver) {
//        messageRepository.find
//    }

    public Message findMessageByid (Long messageId) {
        return messageRepository.findById(messageId).orElseThrow(() -> new GenericNotFoundException("Message not found"));
    }

    public Message saveMessage (Message message) {
        return messageRepository.save(message);
    }

    public void deleteMessage (Long messageId) {
        messageRepository.deleteById(messageId);
    }

}
