package org.example.domain.software.dto.request;

import lombok.Data;

import java.util.Date;
@Data
public class UpdateSoftwareRequestDto {
    private Date expirydate;
    private String assettype;
    private String sn;
    private String dept;
    private String manufacturer;
    private String assetname;
}
