package com.frederikzwartbol.springboottwitterrebuild.features.conversation;

import com.frederikzwartbol.springboottwitterrebuild.features.conversation.models.MessageRequest;
import com.frederikzwartbol.springboottwitterrebuild.features.conversation.models.StartConversationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ConversationController implements ConversationOperations {

    private final ConversationService conversationService;

    @Override
    public ResponseEntity<Conversation> startConversation( StartConversationRequest conversationRequest) {
        log.debug("Conversation start request received {}",conversationRequest);
        return new ResponseEntity<>(conversationService.startConversation(conversationRequest), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Conversation> addMessage(MessageRequest messageRequest) {
        return new ResponseEntity<>(conversationService.addMessageToConversation(messageRequest),HttpStatus.OK);
    }
}
