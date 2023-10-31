package io.service;

import io.model.message.ERole;
import io.model.message.Role;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Override
    public Optional<Role> findByName(ERole role) {
        switch (role) {
            case ROLE_ADMIN -> {
                return Optional.of(new Role(ERole.ROLE_ADMIN));
            }
            case ROLE_MODERATOR -> {
                return Optional.of(new Role(ERole.ROLE_MODERATOR));
            }
            case ROLE_USER -> {
                return Optional.of(new Role(ERole.ROLE_USER));
            }
        }
        return Optional.empty();
    }
}
