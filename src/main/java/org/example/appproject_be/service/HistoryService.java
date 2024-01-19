package org.example.appproject_be.service;

import org.example.appproject_be.dto.HistoryDto;

import java.util.List;

public interface HistoryService {
    HistoryDto historyActionDeleteOrInsert (Long Id, String action, String assetType);
    List<HistoryDto> getHistories();
}
