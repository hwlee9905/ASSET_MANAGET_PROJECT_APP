package org.example.domain.software.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.example.types.Status;

import java.util.Date;

public class Beforejsonsw {
    private Long swidx;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expirydate;
    private String currentuser;
    private Status status;
    private Long assetidx;
    private String assettype;
    private String sn;
    private String dept;
    private String manufacturer;
    private String assetname;
}
