package com.frederikzwartbol.springboottwitterrebuild.features.user.message;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Long> {
}
