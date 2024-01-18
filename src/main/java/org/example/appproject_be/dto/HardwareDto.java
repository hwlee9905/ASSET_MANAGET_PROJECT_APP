package org.example.appproject_be.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.appproject_be.model.Asset;
import org.example.appproject_be.model.Hardware;

import java.time.LocalDateTime;

@Getter@Setter
@RequiredArgsConstructor
public class HardwareDto {
    private Long hwidx;
    private String cpu;
    private String ssd;
    private String hdd;
    private String memory;
    private String status;
    private String usageduration;
    private String returndate;
    private String assigneddate;
    private String currentuser;
    private String previoususer;
    private String location;
    private Long assetidx;
    private String assettype;
    private String sn;
    private String dept;
    private String manufacturer;
    private String assetname;
    private String deadline;
    public static HardwareDto convertToDto(Hardware hardware) {
        HardwareDto hardwareDto = new HardwareDto();
        hardwareDto.setDeadline(hardware.getDeadline());
        hardwareDto.setHwidx(hardware.getHwidx());
        hardwareDto.setCpu(hardware.getCpu());
        hardwareDto.setSsd(hardware.getSsd());
        hardwareDto.setHdd(hardware.getHdd());
        hardwareDto.setMemory(hardware.getMemory());
        hardwareDto.setStatus(hardware.getStatus());
        hardwareDto.setUsageduration(hardware.getUsageduration());
        hardwareDto.setReturndate(hardware.getReturndate());
        hardwareDto.setAssigneddate(hardware.getAssigneddate());
        hardwareDto.setCurrentuser(hardware.getCurrentuser());
        hardwareDto.setPrevioususer(hardware.getPrevioususer());
        hardwareDto.setLocation(hardware.getLocation());
        hardwareDto.setAssetidx(hardware.getAsset().getAssetidx());
        hardwareDto.setAssettype(hardware.getAsset().getAssettype());
        hardwareDto.setSn(hardware.getAsset().getSn());
        hardwareDto.setDept(hardware.getAsset().getDept());
        hardwareDto.setManufacturer(hardware.getAsset().getManufacturer());
        hardwareDto.setAssetname(hardware.getAsset().getAssetname());
        return hardwareDto;
    }
}