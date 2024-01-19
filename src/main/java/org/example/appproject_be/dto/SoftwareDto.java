package org.example.appproject_be.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.appproject_be.model.Asset;
import org.example.appproject_be.model.Hardware;
import org.example.appproject_be.model.Software;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class SoftwareDto {
    private Long swidx;
    private String expirydate;
    private Long assetidx;
    private String assettype;
    private String sn;
    private String dept;
    private String manufacturer;
    private String assetname;
    private String currentuser;

}
