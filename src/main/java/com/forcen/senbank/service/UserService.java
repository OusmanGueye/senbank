package com.forcen.senbank.service;

import com.forcen.senbank.domain.User;
import com.forcen.senbank.service.dto.Statistique;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    Page<User> getAllUsers(Pageable pageable);

    User getUser(Long id);

    User updateUser(User user);

    Statistique getStatistiques();
}
