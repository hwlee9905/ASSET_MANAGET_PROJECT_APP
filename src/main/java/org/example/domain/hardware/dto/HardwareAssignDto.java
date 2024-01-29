package org.example.domain.hardware.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.example.types.Status;

import java.util.Date;
@Data
public class HardwareAssignDto {
    private Long assetidx;
    private Long hwidx;
    @NotNull(message = "currentuser cannot be null")
    private String currentuser;
    private Date assigndate;
    @NotNull(message = "deadline cannot be null")
    private Date deadline;
    private Status status;
}
