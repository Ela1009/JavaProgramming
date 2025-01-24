package com.bookRepo.service;

import java.util.List;
import com.bookRepo.model.Member;
import com.bookRepo.model.Notification;
import com.bookRepo.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.stream.Collectors;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository, SimpMessagingTemplate messagingTemplate) {
        this.notificationRepository = notificationRepository;
        this.messagingTemplate = messagingTemplate;
    }

    public void createNotification(Member member, String message) {
        Notification notification = new Notification();
        notification.setMessage(message);
        notification.setMember(member);
        notification.setDate(LocalDate.now().toString());
        notification.setTime(LocalTime.now().toString());
        notificationRepository.save(notification);
        messagingTemplate.convertAndSend(
                String.format("/topic/notification/%d",member.getId()),
                new Notification(notification.getMessage(),notification.getDate())
        );
    }


    public List<Notification> getNotificationsByMember(Long memberId) {
        return notificationRepository.findByMemberId(memberId).stream()
                .map(notification -> new Notification(notification.getMessage(), notification.getDate()))
                .collect(Collectors.toList());
    }
}
