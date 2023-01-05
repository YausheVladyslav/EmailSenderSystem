package com.example.emailsender.controllers;

import com.example.emailsender.requests.EmailRestRequest;
import com.example.emailsender.responses.LogStatisticResponse;
import com.example.emailsender.services.EmailService;
import com.example.emailsender.services.LogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;
    private final LogService logService;

    @PostMapping("/send-mail")
    public ResponseEntity<Void> sendMail(@Valid @RequestBody EmailRestRequest request) {
        emailService.sendEmail(
                request.getUserId(),
                request.getSubject(),
                request.getMessage()
        );
        return ResponseEntity.ok().build();
    }

    @GetMapping("/statistic")
    public ResponseEntity<List<LogStatisticResponse>> statistic(
            @RequestParam int pageNumber) {
        return ResponseEntity.ok(logService.emailStatistic(pageNumber));
    }
}
