package org.example.appproject_be.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.appproject_be.model.Asset;
import org.example.appproject_be.model.Hardware;

@Getter@Setter
@RequiredArgsConstructor
public class HardwareDto {
    private final Asset asset;
    private final Hardware hardware;
}