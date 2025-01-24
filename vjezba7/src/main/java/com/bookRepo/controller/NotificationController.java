package com.bookRepo.controller;

import com.bookRepo.model.Member;
import com.bookRepo.model.Notification;
import com.bookRepo.repository.MemberRepository;
import com.bookRepo.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/notifications")
public class NotificationController {

    private final NotificationService notificationService;
    private final MemberRepository memberRepository;

    @Autowired
    public NotificationController(NotificationService notificationService, MemberRepository memberRepository) {
        this.notificationService = notificationService;
        this.memberRepository = memberRepository;
    }
    @GetMapping("/send-test-notification")
    public void sendTestNotification() {
        Member member = memberRepository.findById(1L).orElseThrow(()->new RuntimeException("Member not found"));
        String test = "test";
        notificationService.createNotification(member, test);
    }

    @GetMapping("/member/{memberId}")
    public List<Notification> getNotifications(@PathVariable("memberId") Long memberId) {
       return notificationService.getNotificationsByMember(memberId);
    }

}