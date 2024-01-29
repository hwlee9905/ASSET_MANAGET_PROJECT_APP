package org.example.domain.asset.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.example.domain.hardware.dto.HardwareDto;
import org.example.domain.software.dto.SoftwareDto;
import org.example.domain.hardware.entity.Hardware;
import org.example.domain.software.entity.Software;
import org.example.types.Status;

@Data
@Entity
@Table(name="asset")
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assetidx;
    private String assettype;
    @NotNull(message = "Serial Number should not be null or blank")
    @Column(unique = true)
    private String sn;
    private String dept;
    private String manufacturer;
    private String assetname;
    @OneToOne(mappedBy = "asset")
    private Hardware hardware;
    @OneToOne(mappedBy = "asset")
    private Software software;

    public Asset() {

    }

    public void setHardware(Hardware hardware) {
        this.hardware = hardware;
        hardware.setAsset(this);
    }

    public void setSoftware(Software software) {
        this.software = software;
        software.setAsset(this);
    }

    static public Asset createAsset(HardwareDto hardwareDto) {
        Asset asset = new Asset();
        asset.setSn(hardwareDto.getSn());
        asset.setDept(hardwareDto.getDept());
        asset.setAssettype(hardwareDto.getAssettype());
        asset.setManufacturer(hardwareDto.getManufacturer());
        asset.setAssetname(hardwareDto.getAssetname());
        return asset;
    }
    static public Asset createAsset(SoftwareDto softwareDto) {
        Asset asset = new Asset();
        asset.setSn(softwareDto.getSn());
        asset.setDept(softwareDto.getDept());
        asset.setAssettype(softwareDto.getAssettype());
        asset.setManufacturer(softwareDto.getManufacturer());
        asset.setAssetname(softwareDto.getAssetname());
        return asset;
    }
    static public Software createSoftware(SoftwareDto softwareDto) {
        Software software = new Software();
        software.setExpirydate(softwareDto.getExpirydate());
        software.setCurrentuser(softwareDto.getCurrentuser());
        software.setStatus(softwareDto.getStatus());
        return software;
    }
    static public Hardware createHardware(HardwareDto hardwareDto) {
        Hardware hardware = new Hardware();
        hardware.setCpu(hardwareDto.getCpu());
        hardware.setSsd(hardwareDto.getSsd());
        hardware.setHdd(hardwareDto.getHdd());
        hardware.setMemory(hardwareDto.getMemory());
        hardware.setStatus(Status.INACTIVE);
        hardware.setCurrentuser(hardwareDto.getCurrentuser());
        hardware.setPrevioususer(hardwareDto.getPrevioususer());
        hardware.setLocation(hardwareDto.getLocation());
        return hardware;
    }
}
