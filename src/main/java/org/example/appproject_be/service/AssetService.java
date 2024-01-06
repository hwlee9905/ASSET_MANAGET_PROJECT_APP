package org.example.appproject_be.service;

import org.example.appproject_be.model.Asset;

import java.util.List;

public interface AssetService {
    List<Asset> getAssets();
    Asset saveAsset(Asset asset);
}
