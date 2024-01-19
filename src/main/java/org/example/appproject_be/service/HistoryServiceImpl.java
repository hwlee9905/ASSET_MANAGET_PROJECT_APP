package org.example.appproject_be.service;

import lombok.RequiredArgsConstructor;
import org.example.appproject_be.dto.HardwareDto;
import org.example.appproject_be.dto.HistoryDto;
import org.example.appproject_be.model.Hardware;
import org.example.appproject_be.model.History;
import org.example.appproject_be.repository.HistoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {
    private final HistoryRepository historyRepository;
    @Override
    public HistoryDto historyActionDeleteOrInsert(Long Id, String action, String assetType) {
        History history = new History();
        HistoryDto historyDto = new HistoryDto();
        LocalDateTime currentdate = LocalDateTime.now();
        String currentdateToString = currentdate.toString();

        historyDto.setAssetid(Id);
        historyDto.setAssettype(assetType);
        historyDto.setAction(action);
        historyDto.setChangeddate(currentdateToString);

        history.setAssetid(Id);
        history.setAssettype(assetType);
        history.setAction(action);
        history.setChangeddate(currentdateToString);
        historyRepository.save(history);
        return historyDto;
    }

    @Override
    public List<HistoryDto> getHistories() {
        List<History> historyList = historyRepository.findAll();

        List<HistoryDto> historyDtos = historyList.stream()
                .map(history -> {
                    HistoryDto historyDto = new HistoryDto();

                    historyDto.setAssetid(history.getAssetid());
                    historyDto.setAction(history.getAction());
                    historyDto.setHistoryid(history.getHistoryid());
                    historyDto.setChangeddate(history.getChangeddate());
                    historyDto.setJsondata(history.getJsondata());
                    historyDto.setChangedby(history.getChangedby());
                    historyDto.setAssettype(history.getAssettype());
                    return historyDto;
                }).collect(Collectors.toList());

        return historyDtos;
    }
}
