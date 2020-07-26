package com.io.messages.repo;

import com.io.messages.domain.Chat;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ChatRepo extends JpaRepository<Chat, Long> {
      @Query("SELECT c FROM Chat c WHERE c.id = 1")
      Chat getFirst();
}
