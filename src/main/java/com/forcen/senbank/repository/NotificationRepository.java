package com.forcen.senbank.repository;

import com.forcen.senbank.domain.Compte;
import com.forcen.senbank.domain.Notification;
import com.forcen.senbank.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    Page<Notification> findAllByCompte(Compte compte, Pageable pageable);

}
