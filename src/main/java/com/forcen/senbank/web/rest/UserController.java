package com.forcen.senbank.web.rest;


import com.forcen.senbank.domain.User;
import com.forcen.senbank.service.UserService;
import com.forcen.senbank.service.dto.Statistique;
import com.forcen.senbank.service.impl.UserServiceImpl;
import com.forcen.senbank.web.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(@ParameterObject Pageable pageable){
        log.debug("REST request to get all Users");
        Page<User> page = userService.getAllUsers(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(Long id){
        log.debug("REST request to get User : {}", id);
        User user = userService.getUser(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        User updatedUser = userService.updateUser(user);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/statistiques")
    public ResponseEntity<Statistique> getStatistiques(){
        Statistique statistique = userService.getStatistiques();
        return ResponseEntity.ok(statistique);
    }

}
