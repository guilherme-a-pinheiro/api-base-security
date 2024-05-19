package com.pinheiro.loginauthapi.repositories;

import com.pinheiro.loginauthapi.domain.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, String> {
}
