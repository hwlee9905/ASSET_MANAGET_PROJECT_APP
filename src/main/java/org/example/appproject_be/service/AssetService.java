package org.example.appproject_be.service;

import org.example.appproject_be.dto.AssetDto;
import org.example.appproject_be.model.Asset;
import org.example.appproject_be.model.Hardware;

import java.util.List;
import java.util.Optional;

public interface AssetService {
    List<AssetDto> getAssets();
    AssetDto getAsset(Long id);

}
