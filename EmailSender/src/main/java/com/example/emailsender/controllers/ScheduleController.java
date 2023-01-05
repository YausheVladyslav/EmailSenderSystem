package com.example.emailsender.controllers;

import com.example.emailsender.requests.EditExpressionForSchedulerRequest;
import com.example.emailsender.services.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PutMapping("/start-schedule")
    public ResponseEntity<Void> startCron(
            @Valid @RequestBody EditExpressionForSchedulerRequest request
    ) {
        scheduleService.startSchedule(request.getExpression(), request.getSubject());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/edit-schedule")
    public ResponseEntity<Void> edithCron(
            @Valid @RequestBody EditExpressionForSchedulerRequest request
    ) {
        scheduleService.editCron(request.getExpression(), request.getSubject());
        return ResponseEntity.ok().build();
    }
}
