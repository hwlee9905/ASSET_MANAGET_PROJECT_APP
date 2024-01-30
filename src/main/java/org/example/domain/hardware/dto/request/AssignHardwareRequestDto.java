package org.example.domain.hardware.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class AssignHardwareRequestDto {
    @NotNull(message = "currentuser cannot be null")
    private String currentuser;
    @NotNull(message = "deadline cannot be null")
    private Date deadline;
}
