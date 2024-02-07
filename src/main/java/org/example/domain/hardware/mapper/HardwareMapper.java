package org.example.domain.hardware.mapper;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.asset.entity.Asset;
import org.example.domain.hardware.dto.request.AssignHardwareRequestDto;
import org.example.domain.hardware.dto.request.SaveHardwareRequestDto;
import org.example.domain.hardware.dto.request.UpdateHardwareRequestDto;
import org.example.domain.hardware.dto.response.GetHardwaresResponseDto;
import org.example.domain.hardware.entity.Hardware;
import org.example.domain.history.dto.After;
import org.example.domain.history.dto.Before;
import org.example.domain.history.dto.SaveHistoryDto;
import org.example.types.Action;
import org.example.types.Status;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
@Slf4j
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
    public SaveHistoryDto convertSaveHistoryDtoFromAsset(Asset asset) {
        SaveHistoryDto saveHistoryDto = new SaveHistoryDto();
        saveHistoryDto.setAction(Action.INSERT);

        modelMapper.map(asset, saveHistoryDto);
        return saveHistoryDto;
    }
    public void assignHardwareFromDto(AssignHardwareRequestDto assignHardwareDtoRequest, Hardware hardware) {
        modelMapper.map(assignHardwareDtoRequest, hardware);
        hardware.setStatus(Status.ACTIVE);
        hardware.setAssigneddate(new Date());
    }

    public SaveHistoryDto convertDeleteHistoryDtoFromAsset(Asset asset) {
        SaveHistoryDto saveHistoryDto = new SaveHistoryDto();
        saveHistoryDto.setAction(Action.DELETE);
        modelMapper.map(asset, saveHistoryDto);
        return saveHistoryDto;
    }

    public Before convertBeforeFromHardware(Hardware beforehardware) {
        Before before = modelMapper.map(beforehardware, Before.class);
        modelMapper.map(beforehardware.getAsset(), before);
        return before;
    }
    public After convertAfterFromHardware(Hardware afterhardware) {
        After after = modelMapper.map(afterhardware, After.class);
        modelMapper.map(afterhardware.getAsset(), after);
        return after;
    }
}
