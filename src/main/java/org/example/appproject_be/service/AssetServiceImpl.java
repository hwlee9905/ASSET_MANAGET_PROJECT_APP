package org.example.appproject_be.service;

import org.example.appproject_be.model.Asset;
import org.example.appproject_be.model.Hardware;
import org.example.appproject_be.repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssetServiceImpl implements AssetService {
    @Autowired
    private AssetRepository assetRepository;

    @Override
    public List<Asset> getAssets() {
        return assetRepository.findAll();
    }

    @Override
    public Asset getAsset(Long id) {
        Optional<Asset> asset = assetRepository.findById(id);
        if (asset.isPresent()) {
            return asset.get();
        }
        throw new RuntimeException("Asset is not found for the id " + id);

    }

    @Override
    public Asset saveAsset(Asset asset) {
        return assetRepository.save(asset);
    }

    @Override
    public Asset updateAsset(Asset asset) {
        return assetRepository.save(asset);
    }

    @Override
    public void deleteAsset(Long id) {
        assetRepository.deleteById(id);
    }
}
