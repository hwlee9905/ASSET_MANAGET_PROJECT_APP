package org.example.domain.history.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.example.domain.history.dto.Afterjsonhw;
import org.example.domain.history.dto.Afterjsonsw;
import org.example.domain.history.dto.Beforejsonhw;
import org.example.domain.history.dto.Beforejsonsw;


@Getter
@Setter
public class GetHistoriesResponseDto {
    private Long historyidx;
    private String assettype;
    private String action;
    private String changedby;
    private String changeddate;
    private Long assetidx;
    private Afterjsonhw afterjsonhw;
    private Beforejsonhw beforejsonhw;
    private Afterjsonsw afterjsonsw;
    private Beforejsonsw beforejsonsw;
}
