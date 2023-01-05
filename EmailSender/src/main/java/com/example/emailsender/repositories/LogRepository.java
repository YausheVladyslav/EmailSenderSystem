package com.example.emailsender.repositories;

import com.example.emailsender.entities.LogEntity;
import com.example.emailsender.entities.UserEntity;
import com.example.emailsender.enums.MessageType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LogRepository extends JpaRepository<LogEntity, Integer> {

    List<LogEntity> findAllByUser(UserEntity user);

    List<LogEntity> findAllByTypeAndUser(MessageType type, UserEntity user);

}
