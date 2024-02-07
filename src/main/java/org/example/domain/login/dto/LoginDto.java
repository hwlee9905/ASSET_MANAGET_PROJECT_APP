package org.example.domain.login.dto;

import lombok.Data;

@Data
public class LoginDto {
    public static final String LOGIN_MANAGER = "loginManager";
    private String loginId;
    private String password;
    private String email;
    private String name;

}
