package org.example.domain.asset.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.domain.asset.entity.Asset;
import org.example.types.Status;

import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
public class GetAssetsResponseDto {

    private Long assetidx;
    private String assettype;
    private String sn;
    private String dept;
    private String manufacturer;
    private String assetname;
    private Long swidx;
    private Date expirydate;
    private String currentuser;
    private Status status;
    private Long hwidx;
    private String cpu;
    private String ssd;
    private String hdd;
    private String memory;
    private Date deadline;
    private String usageduration;
    private Date returndate;
    private Date assigneddate;
    private String previoususer;
    private String location;
    public static GetAssetsResponseDto convertToDto(Asset asset) {
        GetAssetsResponseDto assetDto = new GetAssetsResponseDto();
        assetDto.setAssetidx(asset.getAssetidx());
        assetDto.setAssettype(asset.getAssettype());
        assetDto.setSn(asset.getSn());
        assetDto.setDept(asset.getDept());
        assetDto.setManufacturer(asset.getManufacturer());
        assetDto.setAssetname(asset.getAssetname());
        return assetDto;
    }
}
