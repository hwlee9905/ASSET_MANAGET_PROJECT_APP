package org.example.appproject_be.dto;

import lombok.Data;

@Data
public class HistoryDto {
    private Long historyid;
    private String assettype;
    private String action;
    private String changedby;
    private String changeddate;

    private String jsondata;
    private Long assetid;
}
