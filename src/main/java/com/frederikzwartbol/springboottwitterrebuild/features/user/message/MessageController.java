package com.frederikzwartbol.springboottwitterrebuild.features.user.message;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageController implements MessageOperations {

    private final MessageService messageService;

}
