package com.ehSolucoes.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ehSolucoes.auth.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
