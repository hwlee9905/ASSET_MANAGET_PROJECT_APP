package org.example.appproject_be.service;

import lombok.RequiredArgsConstructor;
import org.example.appproject_be.dto.SoftwareDto;
import org.example.appproject_be.model.Hardware;
import org.example.appproject_be.model.Software;
import org.example.appproject_be.repository.AssetRepository;
import org.example.appproject_be.repository.HardwareRepository;
import org.example.appproject_be.repository.SoftwareRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SoftwareServiceImpl implements SoftwareService{
    private final SoftwareRepository softwareRepository;
    private final AssetRepository assetRepository;
    @Override
    public Software saveSoftware(Software software){

        try {
            assetRepository.save(software.getAsset());
            software.setSw_idx(software.getAsset().getAsset_idx());
            softwareRepository.save(software);
            return software;
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("이미 등록된 S/N입니다.");
        }
    }

    @Override
    public void deleteSoftware(Long Id) {
        assetRepository.deleteById(Id);
        softwareRepository.deleteById(Id);
    }

    @Override
    public List<SoftwareDto> getSoftwares() {
        List<Software> softwareList = softwareRepository.findAll();

        List<SoftwareDto> softwareDtos = softwareList.stream()
                .sorted((software1, software2) -> software2.getAsset().getAssetName().compareTo(software1.getAsset().getAssetName()))
                .map(software -> {
                    SoftwareDto softwareDto = new SoftwareDto();
                    softwareDto.setAsset_idx(software.getAsset().getAsset_idx());
                    softwareDto.setAssetType(software.getAsset().getAssetType());
                    softwareDto.setSn(software.getAsset().getSn());
                    softwareDto.setDept(software.getAsset().getDept());
                    softwareDto.setManufacturer(software.getAsset().getManufacturer());
                    softwareDto.setAssetName(software.getAsset().getAssetName());
                    softwareDto.setExpiryDate(software.getExpiryDate());

                    return softwareDto;
                })
                .collect(Collectors.toList());

        return softwareDtos;
    }
}
