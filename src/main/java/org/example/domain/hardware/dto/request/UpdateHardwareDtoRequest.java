package org.example.domain.hardware.dto.request;

import lombok.Data;
import org.example.types.Status;

@Data
public class UpdateHardwareDtoRequest {
    private Long assetidx;
    private String cpu;
    private String ssd;
    private String hdd;
    private String memory;
    private Status status;
    private String location;
    private String assettype;
    private String sn;
    private String dept;
    private String manufacturer;
    private String assetname;
}
