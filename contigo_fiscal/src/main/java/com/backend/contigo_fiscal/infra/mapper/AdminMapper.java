package com.backend.contigo_fiscal.infra.mapper;

import com.backend.contigo_fiscal.domain.User;
import com.backend.contigo_fiscal.infra.persistence.entity.AdminEntity;

public class AdminMapper {
    public static User toDomain(AdminEntity entity) {
        return new User(
            entity.getUsername(), 
            entity.getPassword(),
            "ADMIN"             
        );
    }
}
