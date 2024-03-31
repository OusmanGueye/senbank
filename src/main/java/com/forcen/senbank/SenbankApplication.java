package com.forcen.senbank;

import com.forcen.senbank.domain.Role;
import com.forcen.senbank.domain.User;
import com.forcen.senbank.repository.RoleRepository;
import com.forcen.senbank.repository.UserRepository;
import com.forcen.senbank.security.RoleConstant;
import com.forcen.senbank.service.impl.AuthServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootApplication
public class SenbankApplication {

	private final RoleRepository roleRepository;

	private final AuthServiceImpl authService;

    public SenbankApplication(RoleRepository roleRepository, AuthServiceImpl authService) {
        this.roleRepository = roleRepository;
        this.authService = authService;
    }

    public static void main(String[] args) {
		SpringApplication.run(SenbankApplication.class, args);
	}

	@Bean
	CommandLineRunner run() {
		return args -> {
			creationsDesRoles();
			authService.creationAdmin();
		};
	}


	private void creationsDesRoles() {
		if (!roleRepository.existsRoleByName(String.valueOf(RoleConstant.ROLE_USER))) {
			Role role = new Role();
			role.setName(String.valueOf(RoleConstant.ROLE_USER));
			roleRepository.save(role);
		}

		if (!roleRepository.existsRoleByName(String.valueOf(RoleConstant.ROLE_ADMIN))) {
			Role role1 = new Role();
			role1.setName(String.valueOf(RoleConstant.ROLE_ADMIN));
			roleRepository.save(role1);
		}
	}

}
