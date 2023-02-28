package com.frederikzwartbol.springboottwitterrebuild.features.conversation.message;

import com.frederikzwartbol.springboottwitterrebuild.features.conversation.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Long> {

//    Slice<Message> findMessageBy
}
