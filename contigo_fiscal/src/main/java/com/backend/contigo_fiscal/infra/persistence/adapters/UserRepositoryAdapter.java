package com.backend.contigo_fiscal.infra.persistence.adapters;

import com.backend.contigo_fiscal.domain.User;
import com.backend.contigo_fiscal.domain.UserRepository;
import com.backend.contigo_fiscal.infra.mapper.AdminMapper;
import com.backend.contigo_fiscal.infra.persistence.repository.JpaAdminRepository;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class UserRepositoryAdapter implements UserRepository {

    private final JpaAdminRepository jpaAdminRepository;

    public UserRepositoryAdapter(JpaAdminRepository jpaAdminRepository) {
        this.jpaAdminRepository = jpaAdminRepository;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaAdminRepository.findByUsername(email)
                .map(AdminMapper::toDomain);
    }
}