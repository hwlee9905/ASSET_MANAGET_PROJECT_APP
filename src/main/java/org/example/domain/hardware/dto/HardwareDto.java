package org.example.domain.hardware.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.types.Status;

import java.util.Date;

@Getter@Setter
@RequiredArgsConstructor
public class HardwareDto {
    private Long hwidx;
    private String cpu;
    private String ssd;
    private String hdd;
    private String memory;
    private Status status;
    private String usageduration;
    private Date returndate;
    private Date assigneddate;
    private String currentuser;
    private String previoususer;
    private String location;
    private Long assetidx;
    private String assettype;
    private String sn;
    private String dept;
    private String manufacturer;
    private String assetname;
    private Date deadline;

}