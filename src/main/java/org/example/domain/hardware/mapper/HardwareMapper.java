package org.example.domain.hardware.mapper;

import lombok.Data;
import org.example.domain.asset.entity.Asset;
import org.example.domain.hardware.dto.request.SaveHardwareDtoRequest;
import org.example.domain.hardware.dto.request.UpdateHardwareDtoRequest;
import org.example.domain.hardware.dto.response.GetHardwaresDtoResponse;
import org.example.domain.hardware.entity.Hardware;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@Component
public class HardwareMapper {
    private final ModelMapper modelMapper;

    public void updateDtoFromEntity(GetHardwaresDtoResponse getHardwaresDtoResponse,Hardware hardware, Asset asset) {
        modelMapper.map(hardware,getHardwaresDtoResponse);
        modelMapper.map(asset, getHardwaresDtoResponse);
    }
    public Asset createAssetFromDto(SaveHardwareDtoRequest saveHardwareDtoRequest) {

        return modelMapper.map(saveHardwareDtoRequest, Asset.class);
    }
    public Asset updateAssetFromDto(UpdateHardwareDtoRequest updateHardwareDtoRequest) {
        return modelMapper.map(updateHardwareDtoRequest, Asset.class);
    }
}
