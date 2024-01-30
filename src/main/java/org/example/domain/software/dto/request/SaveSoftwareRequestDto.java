package org.example.domain.software.dto.request;

import lombok.Data;
import org.example.types.Status;

import java.util.Date;

@Data
public class SaveSoftwareRequestDto {
    private Date expirydate;
    private String assettype;
    private String sn;
    private String dept;
    private String manufacturer;
    private String assetname;
    private String currentuser;
    private Status status;

}
