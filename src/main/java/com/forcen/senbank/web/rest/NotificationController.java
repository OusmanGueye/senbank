package com.forcen.senbank.web.rest;

import com.forcen.senbank.service.NotificationService;
import com.forcen.senbank.service.dto.NotificationDto;
import com.forcen.senbank.service.impl.NotificationServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    private final Logger log = LoggerFactory.getLogger(NotificationController.class);

    private final NotificationService notificationService;


    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    /**
     *   Methode pour creer une notification
     *   @param idCompte ,notificationDto
     *   @return NotificationDto
     *    @see NotificationServiceImpl#createNotification(NotificationDto)
     **/
    @GetMapping("/compte/{id}")
    public ResponseEntity<List<NotificationDto>> getAllNotification(@PathVariable Long idCompte, @ParameterObject Pageable pageable){
        log.debug("REST request to get all Notification by compte");
        Page<NotificationDto> notificationPage = notificationService.getNotificationByCompte(idCompte, pageable);
        return ResponseEntity.ok().body(notificationPage.getContent());
    }

    /**
     *   Methode pour creer une notification
     *   @param id
     *   @return NotificationDto
     *    @see NotificationServiceImpl#createNotification(NotificationDto)
     **/
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long id){
        log.debug("REST request to delete Notification : {}", id);
        notificationService.deleteNotification(id);
        return ResponseEntity.ok().build();
    }
}
