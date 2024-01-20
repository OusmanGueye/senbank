package com.forcen.senbank.service.impl;

import com.forcen.senbank.domain.Compte;
import com.forcen.senbank.domain.Notification;
import com.forcen.senbank.repository.CompteRepository;
import com.forcen.senbank.repository.NotificationRepository;
import com.forcen.senbank.repository.UserRepository;
import com.forcen.senbank.service.NotificationService;
import com.forcen.senbank.service.dto.NotificationDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class NotificationServiceImpl implements NotificationService {


    private final Logger log = LoggerFactory.getLogger(NotificationServiceImpl.class);

    private final NotificationRepository notificationRepository;

    private final UserRepository userRepository;

    private final CompteRepository compteRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository, UserRepository userRepository, CompteRepository compteRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
        this.compteRepository = compteRepository;
    }


    @Override
    public NotificationDto createNotification(NotificationDto notificationDto) {
        // on recupere le compte
        Compte compte = compteRepository.findById(notificationDto.getCompteId()).orElseThrow(() -> new RuntimeException("Le compte n'existe pas"));

        // on met les infos de la notification
        Notification notification = notificationDto.toEntity();

        // on affecte le compte
        notification.setCompte(compte);
        notification.setDeleted(false);
        notification.setDateNotification(Instant.now());

        return new NotificationDto(notificationRepository.save(notification));
    }

    @Override
    public NotificationDto updateNotification(NotificationDto notificationDto) {
        Notification notification = notificationRepository.findById(notificationDto.getId()).orElseThrow(() -> new RuntimeException("La notification n'existe pas"));
        notification.setMessage(notificationDto.getMessage());

        return new NotificationDto(notificationRepository.save(notification));
    }

    @Override
    public void deleteNotification(Long id) {

        // on recupere la notification
        Notification notification = notificationRepository.findById(id).orElseThrow(() -> new RuntimeException("La notification n'existe pas"));

        // on met deleted a true
        notification.setDeleted(true);

        // on sauvegarde
        notificationRepository.save(notification);
    }

    @Override
    public NotificationDto getNotification(Long id) {
        return notificationRepository.findById(id).map(NotificationDto::new).orElseThrow(() -> new RuntimeException("La notification n'existe pas"));
    }

    @Override
    public Page<NotificationDto> getNotificationByCompte(Long idCompte, Pageable pageable) {

        // on recupere le compte et on verifie s'il existe
        Compte compte = compteRepository.findById(idCompte).orElseThrow(() -> new RuntimeException("Le compte n'existe pas"));

        return notificationRepository.findAllByCompte(compte, pageable).map(NotificationDto::new);
    }
}
