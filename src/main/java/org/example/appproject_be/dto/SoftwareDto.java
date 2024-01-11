package org.example.appproject_be.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.appproject_be.model.Asset;
import org.example.appproject_be.model.Hardware;
import org.example.appproject_be.model.Software;

@Getter
@Setter
@RequiredArgsConstructor
public class SoftwareDto {
    private final Asset asset;
    private final Software software;
}
