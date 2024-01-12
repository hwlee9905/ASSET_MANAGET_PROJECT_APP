package org.example.appproject_be.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.appproject_be.model.Asset;
import org.example.appproject_be.model.Hardware;

import java.time.LocalDateTime;

@Getter@Setter
@RequiredArgsConstructor
public class HardwareDto {
    private Long hw_idx;
    private String cpu;
    private String ssd;
    private String hdd;
    private String memory;
    private String status;
    private LocalDateTime usageDuration;
    private LocalDateTime returnDate;
    private LocalDateTime assignedDate;
    private String currentUser;
    private String previousUser;
    private String location;
    private Long asset_idx;
    private String assetType;
    private String sn;
    private String dept;
    private String manufacturer;
    private String assetName;
}