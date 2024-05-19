package com.pinheiro.loginauthapi.repositories;

import com.pinheiro.loginauthapi.domain.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String login);
}
