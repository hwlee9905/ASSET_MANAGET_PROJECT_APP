package org.example.domain.asset.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.domain.asset.entity.Asset;

@Getter
@Setter
@RequiredArgsConstructor
public class AssetDto {

    private Long assetidx;
    private String assettype;
    private String sn;
    private String dept;
    private String manufacturer;
    private String assetname;
    public static AssetDto convertToDto(Asset asset) {
        AssetDto assetDto = new AssetDto();
        assetDto.setAssetidx(asset.getAssetidx());
        assetDto.setAssettype(asset.getAssettype());
        assetDto.setSn(asset.getSn());
        assetDto.setDept(asset.getDept());
        assetDto.setManufacturer(asset.getManufacturer());
        assetDto.setAssetname(asset.getAssetname());
        return assetDto;
    }
}
