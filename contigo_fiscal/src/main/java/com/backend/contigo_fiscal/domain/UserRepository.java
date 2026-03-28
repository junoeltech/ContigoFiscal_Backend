package com.backend.contigo_fiscal.domain;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByEmail(String email);
}