package org.example.domain.history.dto.response;

import lombok.Data;


@Data
public class GetHistoriesResponseDto {
    private Long historyidx;
    private String assettype;
    private String action;
    private String changedby;
    private String changeddate;
    private Long assetidx;
    private String before_json;
    private String after_json;
}
