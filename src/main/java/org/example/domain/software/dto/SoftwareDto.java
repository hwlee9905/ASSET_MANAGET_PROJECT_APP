package org.example.domain.software.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.types.Status;

import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
public class SoftwareDto {
    private Long swidx;
    private Date expirydate;
    private Long assetidx;
    private String assettype;
    private String sn;
    private String dept;
    private String manufacturer;
    private String assetname;
    private String currentuser;
    private Status status;

}
