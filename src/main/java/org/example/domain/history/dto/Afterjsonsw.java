package org.example.domain.history.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.example.types.Status;

import java.util.Date;
@Getter@Setter
public class Afterjsonsw {
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
