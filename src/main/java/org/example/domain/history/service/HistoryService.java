package org.example.domain.history.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.history.dto.Afterjsonhw;
import org.example.domain.history.dto.Beforejsonhw;
import org.example.domain.history.dto.SaveHistoryDto;
import org.example.domain.history.dto.response.GetHistoriesResponseDto;
import org.example.domain.history.entity.History;
import org.example.domain.history.util.HistoryMapper;
import org.example.domain.history.repository.HistoryRepository;
import org.example.domain.history.dto.Afterjsonsw;
import org.example.domain.history.dto.Beforejsonsw;
import org.example.types.Action;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class HistoryService {
    private final HistoryRepository historyRepository;
    private final HistoryMapper historyMapper;
    public void historyActionDeleteOrInsert(SaveHistoryDto saveHistoryDto) {
        History history = historyMapper.createHistoryFromDto(saveHistoryDto);
        historyRepository.save(history);
    }
    public void historyActionUpdateHw(Beforejsonhw before, Afterjsonhw after) {
        History history = historyMapper.createHistoryFromBeforeAfter(before, after);
        history.setAction(Action.UPDATE);

        historyRepository.save(history);
    }
    public void historyActionUpdateSw(Beforejsonsw before, Afterjsonsw after) {
        History history = historyMapper.createHistoryFromBeforeAfter(before, after);
        history.setAction(Action.UPDATE);

        historyRepository.save(history);
    }
    public List<GetHistoriesResponseDto> getHistories() {

        List<History> historyList = historyRepository.findAll();

        return historyList.stream()
                .map(history -> {
                    GetHistoriesResponseDto getHistoriesResponseDto = new GetHistoriesResponseDto();
                    historyMapper.convertHistoryFromDto(getHistoriesResponseDto, history);
                    return getHistoriesResponseDto;
                }).collect(Collectors.toList());
    }
}
