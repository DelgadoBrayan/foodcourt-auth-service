package com.auth.service.domain.spi;

import com.auth.service.domain.model.user.Role;

public interface IRolePersistencePort {
    Role findByName(String name);
    Role save(Role role);
    Role findById(Long roleId);
}
