package org.example.domain.history.dto;

import lombok.Data;
import org.example.types.Action;

@Data
public class SaveHistoryDto {
    private String assettype;
    private Action action;
    private String changedby;
    private Long assetidx;
}
