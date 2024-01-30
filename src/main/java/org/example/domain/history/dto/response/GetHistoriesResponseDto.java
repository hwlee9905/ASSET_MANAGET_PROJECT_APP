package org.example.domain.history.dto.response;

import lombok.Data;

@Data
public class GetHistoriesResponseDto {
    private Long historyid;
    private String assettype;
    private String action;
    private String changedby;
    private String changeddate;
    private String jsondata;
    private Long assetid;
}
