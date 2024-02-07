package org.example.domain.history.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.example.domain.history.dto.After;
import org.example.domain.history.dto.Before;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class GetHistoriesResponseDto {
    private Long historyidx;
    private String assettype;
    private String action;
    private String changedby;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String changeddate;
    private String jsondata;
    private Long assetidx;
    private Before before;
    private After after;
}
