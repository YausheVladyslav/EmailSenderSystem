package com.example.emailsender.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "cron_jobs")
public class CronEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cronId;
    private String expression;
    @CreationTimestamp
    private LocalDateTime createdOn;
}
