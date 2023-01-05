package com.example.emailsender.controllers;

import com.example.emailsender.entities.CronEntity;
import com.example.emailsender.requests.CreateCronRequest;
import com.example.emailsender.services.CronService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CronController {

    private final CronService cronService;

    @PostMapping("/add-cron")
    public ResponseEntity<Void> addCron(@Valid @RequestBody CreateCronRequest createRequest) {
        cronService.createCron(createRequest.getCron());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/edit-cron")
    public ResponseEntity<Void> editCron(
            @RequestParam int cronId,
            @Valid
            @RequestBody CreateCronRequest editRequest
    ) {
        cronService.editCron(cronId, editRequest.getCron());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete-cron")
    public ResponseEntity<Void> deleteCron(@RequestParam int cronId) {
        cronService.deleteCron(cronId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/cron-list")
    public ResponseEntity<Page<CronEntity>> usersList(@RequestParam int pageNumber) {
        return ResponseEntity.ok(cronService.allCrons(pageNumber));
    }
}
