package com.pinheiro.loginauthapi.domain.User;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "user")
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    private String email;
    private String password;
}
