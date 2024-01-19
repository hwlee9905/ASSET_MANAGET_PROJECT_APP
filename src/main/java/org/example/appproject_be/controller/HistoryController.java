package org.example.appproject_be.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.appproject_be.dto.HardwareDto;
import org.example.appproject_be.dto.HistoryDto;
import org.example.appproject_be.service.HistoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //@Controller + @ResponseBody
@RequiredArgsConstructor
@Slf4j
public class HistoryController {
    private final HistoryService historyService;
    @GetMapping("/histories") //@RequestMapping(value = "/assets", method = RequestMethod.GET)
    public List<HistoryDto> gethistories() {
        return historyService.getHistories();
    }
}
