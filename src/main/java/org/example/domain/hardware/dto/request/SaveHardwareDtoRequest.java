package org.example.domain.hardware.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.example.types.Status;

import java.util.Date;

@Getter@Setter
public class SaveHardwareDtoRequest {
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
