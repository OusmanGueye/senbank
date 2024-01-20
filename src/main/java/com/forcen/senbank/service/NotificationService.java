package com.forcen.senbank.service;

import com.forcen.senbank.service.dto.NotificationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NotificationService  {

    /**
     * Methode pour creer une notification
     * @param notificationDto
     * @return NotificationDto
     * @see com.forcen.senbank.service.impl.NotificationServiceImpl#createNotification(NotificationDto)
     **/
    NotificationDto createNotification(NotificationDto notificationDto);

    /**
     * Methode pour mettre a jour une notification
     * @param notificationDto
     * @return NotificationDto
     * @see com.forcen.senbank.service.impl.NotificationServiceImpl#updateNotification(NotificationDto)
     **/
    NotificationDto updateNotification(NotificationDto notificationDto);

    /**
     * Methode pour supprimer une notification
     * @param id
     * @see com.forcen.senbank.service.impl.NotificationServiceImpl#deleteNotification(Long)
     **/
    void deleteNotification(Long id);

    /**
     * Methode pour recuperer une notification par son id
     * @param id
     * @return NotificationDto
     * @see com.forcen.senbank.service.impl.NotificationServiceImpl#getNotification(Long)
     **/
    NotificationDto getNotification(Long id);

    /**
     * Methode pour recuperer toutes les notifications
     * @param pageable
     * @return Page<NotificationDto>
     * @see com.forcen.senbank.service.impl.NotificationServiceImpl#getNotificationByCompte(Long, Pageable)
     **/
    Page<NotificationDto> getNotificationByCompte(Long idCompte, Pageable pageable);
}
