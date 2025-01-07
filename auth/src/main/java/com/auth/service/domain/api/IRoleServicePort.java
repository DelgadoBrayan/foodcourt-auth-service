package com.auth.service.domain.api;

import com.auth.service.domain.model.user.Role;

public interface IRoleServicePort {
    Role findByName(String name);
    Role save(Role role);
    Role findById(Long id);
}
