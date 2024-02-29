package org.example.domain.software.dto.request;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

import java.util.Date;
@Data
public class UpdateSoftwareRequestDto {
    @Temporal(TemporalType.TIMESTAMP)
    private Date expirydate;
    private String assettype;
    private String sn;
    private String dept;
    private String manufacturer;
    private String assetname;
}
