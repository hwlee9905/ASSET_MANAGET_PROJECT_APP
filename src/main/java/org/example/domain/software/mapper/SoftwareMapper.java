package org.example.domain.software.mapper;

import lombok.Data;
import org.example.domain.asset.entity.Asset;
import org.example.domain.history.dto.SaveHistoryDto;
import org.example.domain.software.dto.Afterjsonsw;
import org.example.domain.software.dto.Beforejsonsw;
import org.example.domain.software.dto.request.SaveSoftwareRequestDto;
import org.example.domain.software.dto.request.UpdateSoftwareRequestDto;
import org.example.domain.software.dto.response.GetSoftwaresResponseDto;
import org.example.domain.software.entity.Software;
import org.example.types.Action;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Data
@Component
public class SoftwareMapper {
    private final ModelMapper modelMapper;

    public Asset createAssetFromDto(SaveSoftwareRequestDto saveSoftwareRequestDto) {
        return modelMapper.map(saveSoftwareRequestDto , Asset.class);
    }
    public void convertSoftwareFromDto(UpdateSoftwareRequestDto updateSoftwareRequestDto, Software software) {
        modelMapper.map(updateSoftwareRequestDto, software);
        modelMapper.map(updateSoftwareRequestDto, software.getAsset());
    }
    public void convertDtoFromEntity(GetSoftwaresResponseDto getSoftwaresResponseDto, Software software, Asset asset) {
        modelMapper.map(software, getSoftwaresResponseDto);
        modelMapper.map(asset, getSoftwaresResponseDto);
    }
    public SaveHistoryDto convertSaveHistoryDtoFromAsset(Asset asset) {
        SaveHistoryDto saveHistoryDto = new SaveHistoryDto();
        saveHistoryDto.setAction(Action.INSERT);

        modelMapper.map(asset, saveHistoryDto);
        return saveHistoryDto;
    }

    public SaveHistoryDto convertDeleteHistoryDtoFromAsset(Asset asset) {
        SaveHistoryDto saveHistoryDto = new SaveHistoryDto();
        saveHistoryDto.setAction(Action.DELETE);

        modelMapper.map(asset, saveHistoryDto);
        return saveHistoryDto;
    }
    public Beforejsonsw convertBeforeFromSoftware(Software beforeSoftware) {
        Beforejsonsw before = modelMapper.map(beforeSoftware, Beforejsonsw.class);
        modelMapper.map(beforeSoftware.getAsset(), before);
        return before;
    }
    public Afterjsonsw convertAfterFromSoftware(Software afterSoftware) {
        Afterjsonsw after = modelMapper.map(afterSoftware, Afterjsonsw.class);
        modelMapper.map(afterSoftware.getAsset(), after);
        return after;
    }
}
