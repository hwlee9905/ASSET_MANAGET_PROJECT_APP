package org.example.appproject_be.service;

import org.example.appproject_be.model.ErrorResult;
import org.example.appproject_be.model.Manager;
import org.springframework.http.ResponseEntity;

public interface LoginService {
    Manager login(String loginId, String password);

}
