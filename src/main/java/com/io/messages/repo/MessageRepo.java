package com.io.messages.repo;

import com.io.messages.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;



public interface MessageRepo extends JpaRepository<Message, Long> {

}
