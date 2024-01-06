package org.example.appproject_be.service;

import org.example.appproject_be.model.Asset;

import java.util.List;
import java.util.Optional;

public interface AssetService {
    List<Asset> getAssets();
    Optional<Asset> getAsset(Long id);
    Asset saveAsset(Asset asset);
}
