package com.forcen.senbank.repository;

import com.forcen.senbank.domain.Role;
import com.forcen.senbank.security.RoleConstant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);

}
