package com.forcen.senbank.service.dto;

import com.forcen.senbank.domain.Compte;
import com.forcen.senbank.domain.Notification;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDto {

    private Long id;

    private String message;

    private String type;

    private String lien;

    private boolean isRead;

    private Compte compte;

    private Long compteId;

    public NotificationDto(Notification notification) {
        this.id = notification.getId();
        this.message = notification.getMessage();
        this.compteId = notification.getCompte().getId();
        this.compte = notification.getCompte();
    }

    public Notification toEntity() {
        Notification notification = new Notification();
        notification.setId(this.id);
        notification.setMessage(this.message);
        return notification;
    }
}
