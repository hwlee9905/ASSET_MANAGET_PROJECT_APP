package org.example.domain.history.util;

import lombok.Data;
import org.example.domain.history.dto.SaveHistoryDto;
import org.example.domain.history.dto.response.GetHistoriesResponseDto;
import org.example.domain.history.entity.History;
import org.example.domain.history.dto.Afterjsonhw;
import org.example.domain.history.dto.Beforejsonhw;
import org.example.domain.manager.entity.Manager;
import org.example.domain.software.dto.Afterjsonsw;
import org.example.domain.software.dto.Beforejsonsw;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
public class HistoryMapper {
    private final ModelMapper modelMapper;
    private Manager loginMember;
    public void convertHistoryFromDto(GetHistoriesResponseDto getHistoriesResponseDto, History history) {
        modelMapper.map(history,getHistoriesResponseDto);
    }
    public History createHistoryFromDto(SaveHistoryDto saveHistoryDto) {
        History history = modelMapper.map(saveHistoryDto , History.class);
        history.setChangeddate(new Date());
        history.setChangedby(loginMember.getName());
        return history;
    }
    public History createHistoryFromBeforeAfter(Beforejsonhw before, Afterjsonhw after) {
        History history = modelMapper.map(before , History.class);
        modelMapper.map(after, history);
        history.setChangeddate(new Date());
        history.setChangedby(loginMember.getName());
        return history;
    }
    public History createHistoryFromBeforeAfter(Beforejsonsw before, Afterjsonsw after) {
        History history = modelMapper.map(before , History.class);
        modelMapper.map(after, history);
        history.setChangeddate(new Date());
        history.setChangedby(loginMember.getName());
        return history;
    }
}
