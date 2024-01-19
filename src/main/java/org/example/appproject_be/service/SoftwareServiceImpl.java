package org.example.appproject_be.service;

import lombok.RequiredArgsConstructor;
import org.example.appproject_be.dto.SoftwareDto;
import org.example.appproject_be.model.Asset;
import org.example.appproject_be.model.Hardware;
import org.example.appproject_be.model.Software;
import org.example.appproject_be.repository.AssetRepository;
import org.example.appproject_be.repository.HardwareRepository;
import org.example.appproject_be.repository.SoftwareRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SoftwareServiceImpl implements SoftwareService{
    private final SoftwareRepository softwareRepository;
    private final AssetRepository assetRepository;
    @Override
    public SoftwareDto saveSoftware(SoftwareDto softwareDto){

        try {
            Asset asset = Asset.createAsset(softwareDto);
            assetRepository.save(asset);
            Software software = Asset.createSoftware(softwareDto);
            asset.setSoftware(software);
            softwareRepository.save(asset.getSoftware());

            softwareDto.setAssetidx(asset.getAssetidx());
            softwareDto.setSwidx(asset.getSoftware().getSwidx());
            return softwareDto;
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("이미 등록된 S/N입니다.");
        }
    }

    @Override
    public SoftwareDto updateSoftware(SoftwareDto softwareDto) {
        Asset asset = Asset.createAsset(softwareDto);
        asset.setAssetidx(softwareDto.getAssetidx());
        assetRepository.save(asset);
        Software software = Asset.createSoftware(softwareDto);
        software.setSwidx(softwareDto.getSwidx());
        asset.setSoftware(software);
        softwareRepository.save(asset.getSoftware());

        return softwareDto;
    }

    @Override
    public void deleteSoftware(Long Id) {

        Optional<Software> softwareOptional = softwareRepository.findById(Id);
        if (softwareOptional.isPresent()) {
            softwareRepository.deleteById(Id);
        } else {
            throw new DataRetrievalFailureException("Software not found with ID: " + Id);
        }

    }

    @Override
    public List<SoftwareDto> getSoftwares() {
        List<Software> softwareList = softwareRepository.findAll();

        List<SoftwareDto> softwareDtos = softwareList.stream()
                .map(software -> {
                    SoftwareDto softwareDto = new SoftwareDto();
                    softwareDto.setSwidx(software.getSwidx());
                    softwareDto.setAssetidx(software.getAsset().getAssetidx());
                    softwareDto.setAssettype(software.getAsset().getAssettype());
                    softwareDto.setSn(software.getAsset().getSn());
                    softwareDto.setDept(software.getAsset().getDept());
                    softwareDto.setManufacturer(software.getAsset().getManufacturer());
                    softwareDto.setAssetname(software.getAsset().getAssetname());
                    softwareDto.setExpirydate(software.getExpirydate());
                    softwareDto.setCurrentuser(software.getCurrentuser());
                    return softwareDto;
                })
                .collect(Collectors.toList());

        return softwareDtos;
    }

    @Override
    public List<SoftwareDto> getSoftwares(String sortAttr, String sortOrder) {
        List<Software> softwareList;
        if (sortAttr != null && !sortAttr.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
            if (isSortAttrInAsset(sortAttr)) {
                String sortBy = "asset." + sortAttr;
                softwareList = softwareRepository.findAll(Sort.by(Sort.Direction.fromString(sortOrder), sortBy));
            } else {
                softwareList = softwareRepository.findAll(Sort.by(Sort.Direction.fromString(sortOrder), sortAttr));
            }
        } else {
            throw new MissingFormatArgumentException("Both sort attribute (sortAttr) and sort order (sortOrder) must be provided");
        }

        List<SoftwareDto> softwareDtos = softwareList.stream()
                .map(software -> {
                    SoftwareDto softwareDto = new SoftwareDto();
                    softwareDto.setAssetidx(software.getAsset().getAssetidx());
                    softwareDto.setAssettype(software.getAsset().getAssettype());
                    softwareDto.setSn(software.getAsset().getSn());
                    softwareDto.setDept(software.getAsset().getDept());
                    softwareDto.setManufacturer(software.getAsset().getManufacturer());
                    softwareDto.setAssetname(software.getAsset().getAssetname());
                    softwareDto.setSwidx(software.getSwidx());
                    softwareDto.setExpirydate(software.getExpirydate());
                    softwareDto.setCurrentuser(software.getCurrentuser());
                    return softwareDto;
                }).toList();
        return softwareDtos;
    }

    @Override
    public SoftwareDto getSoftware(Long id) {
        Optional<Software> softwareOptional = softwareRepository.findById(id);

        if (softwareOptional.isPresent()) {
            Software software = softwareOptional.get();
            SoftwareDto softwareDto = new SoftwareDto();

            // set asset
            softwareDto.setAssetidx(software.getAsset().getAssetidx());
            softwareDto.setAssettype(software.getAsset().getAssettype());
            softwareDto.setSn(software.getAsset().getSn());
            softwareDto.setDept(software.getAsset().getDept());
            softwareDto.setManufacturer(software.getAsset().getManufacturer());
            softwareDto.setAssetname(software.getAsset().getAssetname());

            // set software-specific properties
            softwareDto.setSwidx(software.getSwidx());
            softwareDto.setExpirydate(software.getExpirydate());
            softwareDto.setCurrentuser(software.getCurrentuser());
            return softwareDto;
        } else {
            // Handle the case where software with the given ID is not found
            throw new DataRetrievalFailureException("Software not found with ID: " + id);
            // Or return null, depending on your application's logic
        }
    }
    private boolean isSortAttrInAsset(String sortAttr) {
        Set<String> assetProperties = new HashSet<>(Arrays.asList(
                "assetidx", "assetType", "sn", "dept", "manufacturer", "assetName"
        ));

        return assetProperties.contains(sortAttr);
    }
}
