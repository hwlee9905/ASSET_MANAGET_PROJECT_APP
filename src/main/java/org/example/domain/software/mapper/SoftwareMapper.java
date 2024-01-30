package org.example.domain.software.mapper;

import lombok.Data;
import org.example.domain.asset.entity.Asset;
import org.example.domain.hardware.dto.request.SaveHardwareRequestDto;
import org.example.domain.software.dto.request.SaveSoftwareRequestDto;
import org.example.domain.software.entity.Software;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
@Data
@Component
public class SoftwareMapper {
    private final ModelMapper modelMapper;
    public Asset createAssetFromDto(SaveSoftwareRequestDto saveSoftwareRequestDto) {
        return modelMapper.map(saveSoftwareRequestDto , Asset.class);
    }
}
