package org.example.appproject_be.service;

import org.example.appproject_be.model.Asset;
import org.example.appproject_be.model.Hardware;

import java.util.List;
import java.util.Optional;

public interface AssetService {
    List<Asset> getAssets();
    Asset getAsset(Long id);
    Asset saveAsset(Asset asset);

    void deleteAsset(Long id);
    Asset updateAsset(Asset asset);

}
