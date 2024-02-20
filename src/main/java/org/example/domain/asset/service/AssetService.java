package org.example.domain.asset.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.asset.dto.GetAssetsResponseDto;
import org.example.domain.asset.entity.Asset;
import org.example.domain.asset.repository.AssetRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AssetService {
    private final AssetRepository assetRepository;

    public List<GetAssetsResponseDto> getAssets() {
        List<Asset> assets = assetRepository.findAll();
        return assets.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private GetAssetsResponseDto convertToDto(Asset asset) {
        GetAssetsResponseDto assetDto = new GetAssetsResponseDto();
        BeanUtils.copyProperties(asset, assetDto);
        return assetDto;
    }

    public GetAssetsResponseDto getAsset(Long id) {
        Optional<Asset> asset = assetRepository.findById(id);
        if (asset.isPresent()) {
            return asset.map(GetAssetsResponseDto::convertToDto).orElse(null);
        }
        throw new DataRetrievalFailureException("Asset not found with ID: " + id);
    }
}
