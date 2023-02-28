package com.frederikzwartbol.springboottwitterrebuild.features.conversation.message;

import com.frederikzwartbol.springboottwitterrebuild.features.user.UserOperations;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(UserOperations.PREFIX)
public interface MessageOperations {

    String POSTFIX = "messages/";


}
