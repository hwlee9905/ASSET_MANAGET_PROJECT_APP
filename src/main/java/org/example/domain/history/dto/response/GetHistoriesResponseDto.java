package org.example.domain.history.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.example.domain.history.dto.Afterjson;
import org.example.domain.history.dto.Beforejson;


@Getter
@Setter
public class GetHistoriesResponseDto {
    private Long historyidx;
    private String assettype;
    private String action;
    private String changedby;
    private String changeddate;
    private Long assetidx;
    private Afterjson beforejson;
    private Beforejson afterjson;
}
