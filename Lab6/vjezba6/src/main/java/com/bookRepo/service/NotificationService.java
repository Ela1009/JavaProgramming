package com.bookRepo.service;

import java.util.List;
import com.bookRepo.model.Member;
import com.bookRepo.model.Notification;
import com.bookRepo.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.stream.Collectors;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void createNotification(Member member, String message) {
        Notification notification = new Notification();
        notification.setMessage(message);
        notification.setMember(member);
        notification.setDate(LocalDate.now().toString());
        notification.setTime(LocalTime.now().toString());
        notificationRepository.save(notification);
    }
    public List<Notification> getNotificationsByMember(Long memberId) {
        return notificationRepository.findByMemberId(memberId).stream()
                .map(notification -> new Notification(notification.getMessage(), notification.getDate()))
                .collect(Collectors.toList());
    }
}
