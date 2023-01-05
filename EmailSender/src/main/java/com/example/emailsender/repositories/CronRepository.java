package com.example.emailsender.repositories;

import com.example.emailsender.entities.CronEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CronRepository extends JpaRepository<CronEntity, Integer> {
}
