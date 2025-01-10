package com.bookRepo.controller;

import com.bookRepo.model.Notification;
import com.bookRepo.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/member/{memberId}")
    public List<Notification> getNotifications(@PathVariable("memberId") Long memberId) {
       return notificationService.getNotificationsByMember(memberId);
    }

}