package org.example.domain.history.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.example.domain.history.dto.Afterjsonhw;
import org.example.domain.history.dto.Beforejsonhw;


@Getter
@Setter
public class GetHistoriesResponseDto {
    private Long historyidx;
    private String assettype;
    private String action;
    private String changedby;
    private String changeddate;
    private Long assetidx;
    private Afterjsonhw beforejson;
    private Beforejsonhw afterjson;
}
