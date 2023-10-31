package io.service;

import io.model.message.ERole;
import io.model.message.Role;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findByName(ERole role);
}
