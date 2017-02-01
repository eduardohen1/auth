package com.ehSolucoes.auth.service;

import com.ehSolucoes.auth.model.User;

public interface UserService {
	void save(User user);
	User findByUsername(String username);
}
