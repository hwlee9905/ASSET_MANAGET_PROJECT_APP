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
    private Long hwidx;
    private String cpu;
    private String ssd;
    private String hdd;
    private String memory;
    private String status;
    private LocalDateTime usageduration;
    private LocalDateTime returndate;
    private LocalDateTime assigneddate;
    private String currentuser;
    private String previoususer;
    private String location;
    private Long assetidx;
    private String assettype;
    private String sn;
    private String dept;
    private String manufacturer;
    private String assetname;
}