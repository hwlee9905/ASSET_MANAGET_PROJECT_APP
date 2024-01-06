package org.example.appproject_be.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Asset {
    @JsonIgnore
    private Long index;
    private String assetType;
    private String sn;
    private String dept;
    private String manufacturer;
    private String assetName;



}
