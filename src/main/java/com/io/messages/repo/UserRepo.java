package com.io.messages.repo;

import com.io.messages.domain.Chat;
import com.io.messages.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}
