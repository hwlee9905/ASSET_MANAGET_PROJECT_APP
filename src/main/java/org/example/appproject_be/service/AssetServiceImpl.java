package org.example.appproject_be.service;

import org.example.appproject_be.model.Asset;
import org.example.appproject_be.repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssetServiceImpl implements AssetService {
    @Autowired
    private AssetRepository assetRepository;

    @Override
    public List<Asset> getAssets() {
        return assetRepository.findAll();
    }

    @Override
    public Asset saveAsset(Asset asset) {
        return assetRepository.save(asset);
    }
}
