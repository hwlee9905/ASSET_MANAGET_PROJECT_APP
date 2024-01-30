package org.example.domain.history.mapper;

import lombok.Data;
import org.example.domain.asset.entity.Asset;
import org.example.domain.history.dto.SaveHistoryDto;
import org.example.domain.history.dto.response.GetHistoriesResponseDto;
import org.example.domain.history.entity.History;
import org.example.domain.software.dto.request.SaveSoftwareRequestDto;
import org.example.domain.software.dto.request.UpdateSoftwareRequestDto;
import org.example.domain.software.entity.Software;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Component
@Data
public class HistoryMapper {
    private final ModelMapper modelMapper;
    public void convertHistoryFromDto(GetHistoriesResponseDto getHistoriesResponseDto, History history) {
        modelMapper.map(getHistoriesResponseDto, history);
    }
    public History createHistoryFromDto(SaveHistoryDto saveHistoryDto) {
        History history = modelMapper.map(saveHistoryDto , History.class);
        history.setChangeddate(new Date());
        return history;
    }
}
