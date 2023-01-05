package com.example.emailsender.scheduler;

import com.example.emailsender.requests.EditExpressionForSchedulerRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService myCronTrigger;

    @PutMapping("/start-thread")
    public ResponseEntity<String> startThread(
            @Valid @RequestBody EditExpressionForSchedulerRequest request
    ) {
        myCronTrigger.startSchedule(request.getExpression(), request.getSubject());
        return ResponseEntity.ok("Thread is started");
    }

    @PutMapping("/switch-cron")
    public ResponseEntity<String> switchCron(
            @Valid @RequestBody EditExpressionForSchedulerRequest request
    ) {
        myCronTrigger.editCron(request.getExpression(), request.getSubject());
        return ResponseEntity.ok("Works switched cron");
    }
}
