package org.example.domain.hardware.mapper;

import lombok.Data;
import org.example.domain.asset.entity.Asset;
import org.example.domain.hardware.dto.request.AssignHardwareRequestDto;
import org.example.domain.hardware.dto.request.SaveHardwareRequestDto;
import org.example.domain.hardware.dto.request.UpdateHardwareRequestDto;
import org.example.domain.hardware.dto.response.GetHardwaresResponseDto;
import org.example.domain.hardware.entity.Hardware;
import org.example.types.Status;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class HardwareMapper {
    private final ModelMapper modelMapper;

    public void convertDtoFromEntity(GetHardwaresResponseDto getHardwaresResponseDto, Hardware hardware, Asset asset) {
        modelMapper.map(hardware, getHardwaresResponseDto);
        modelMapper.map(asset, getHardwaresResponseDto);
    }
    public Asset createAssetFromDto(SaveHardwareRequestDto saveHardwareRequestDto) {
        return modelMapper.map(saveHardwareRequestDto, Asset.class);
    }
    public void convertHardwareFromDto(UpdateHardwareRequestDto updateHardwareRequestDto, Hardware hardware) {
        modelMapper.map(updateHardwareRequestDto, hardware);
        modelMapper.map(updateHardwareRequestDto, hardware.getAsset());
    }
    public void assignHardwareFromDto(AssignHardwareRequestDto assignHardwareDtoRequest, Hardware hardware) {
        modelMapper.map(assignHardwareDtoRequest, hardware);
        hardware.setStatus(Status.ACTIVE);
        hardware.setAssigneddate(new Date());
    }
}
