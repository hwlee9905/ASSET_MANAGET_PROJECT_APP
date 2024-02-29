package org.example.domain.software.dto.response;

import lombok.Data;
import org.example.types.Status;

import java.util.Date;
@Data
public class GetSoftwaresResponseDto {
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
