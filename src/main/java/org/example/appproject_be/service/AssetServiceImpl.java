package org.example.appproject_be.service;

import lombok.RequiredArgsConstructor;
import org.example.appproject_be.dto.AssetDto;
import org.example.appproject_be.model.Asset;
import org.example.appproject_be.model.Hardware;
import org.example.appproject_be.repository.AssetRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class AssetServiceImpl implements AssetService {
    private final AssetRepository assetRepository;

    public List<AssetDto> getAssets() {
        List<Asset> assets = assetRepository.findAll();
        return assets.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private AssetDto convertToDto(Asset asset) {
        AssetDto assetDto = new AssetDto();
        BeanUtils.copyProperties(asset, assetDto);
        return assetDto;
    }

    @Override
    public AssetDto getAsset(Long id) {
        Optional<Asset> asset = assetRepository.findById(id);
        if (asset.isPresent()) {
            return asset.map(AssetDto::convertToDto).orElse(null);
        }
        throw new DataRetrievalFailureException("Asset not found with ID: " + id);
    }

    @Override
    public Asset saveAsset(Asset asset) {
        return assetRepository.save(asset);
    }

    @Override
    public Asset updateAsset(Asset asset) {
        return assetRepository.save(asset);
    }


}
