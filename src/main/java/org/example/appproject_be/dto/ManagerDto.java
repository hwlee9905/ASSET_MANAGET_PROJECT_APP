package org.example.appproject_be.dto;

import lombok.Data;

@Data
public class ManagerDto {
    public static final String LOGIN_MANAGER = "loginManager";
    private String loginId;
    private String password;
    private String email;
    private String name;

}
