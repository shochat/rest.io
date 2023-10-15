package io.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserInfoResponse {
    private long id;
    private String username;
    private String email;
    private List<String> roles;
    private String cookie;
}
