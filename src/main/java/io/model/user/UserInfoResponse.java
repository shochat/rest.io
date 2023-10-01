package io.model.user;

import io.model.message.ERole;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
public class UserInfoResponse {
    private long id;
    private String username;
    private String email;
    private List<String> roles;
}
