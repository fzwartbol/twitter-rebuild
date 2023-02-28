package com.frederikzwartbol.springboottwitterrebuild.features.conversation;

import com.frederikzwartbol.springboottwitterrebuild.features.conversation.models.MessageRequest;
import com.frederikzwartbol.springboottwitterrebuild.features.conversation.models.StartConversationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(ConversationOperations.PREFIX)
public interface ConversationOperations {

    String PREFIX = "/conversations";
    @PostMapping
    ResponseEntity<Conversation> startConversation(@RequestBody StartConversationRequest startConversationRequest);

    @PostMapping("/messages")
    ResponseEntity<Conversation> addMessage(@RequestBody MessageRequest messageRequest);

}
