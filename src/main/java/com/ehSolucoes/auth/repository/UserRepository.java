package com.ehSolucoes.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ehSolucoes.auth.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
}
