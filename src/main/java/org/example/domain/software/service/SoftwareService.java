package org.example.domain.software.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.asset.entity.Asset;
import org.example.domain.asset.repository.AssetRepository;
import org.example.domain.hardware.entity.Hardware;
import org.example.domain.hardware.exception.DataDuplicationViolationException;
import org.example.domain.hardware.exception.HardwareNotFoundException;
import org.example.domain.history.service.HistoryService;
import org.example.domain.software.dto.request.SaveSoftwareRequestDto;
import org.example.domain.software.entity.Software;
import org.example.domain.software.dto.SoftwareDto;
import org.example.domain.software.mapper.SoftwareMapper;
import org.example.domain.software.repository.SoftwareRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SoftwareService{
    private final SoftwareRepository softwareRepository;
    private final AssetRepository assetRepository;
    private final HistoryService historyService;
    private final SoftwareMapper softwareMapper;
    public void saveSoftware(SaveSoftwareRequestDto saveSoftwareRequestDto){
        try{
            Asset asset = softwareMapper.createAssetFromDto(saveSoftwareRequestDto);

            softwareRepository.save(asset.getSoftware()); // Software 저장
            assetRepository.save(asset);

            //history save logic
            historyService.historyActionDeleteOrInsert(asset.getAssetidx(), "INSERT", asset.getAssettype());
        } catch (RuntimeException e) {

        }
    }
    public void updateSoftware(SoftwareDto softwareDto) {
        Optional<Software> softwareOptional = softwareRepository.findById(softwareDto.getSwidx());
        if (softwareOptional.isPresent()) {
            if (softwareOptional.get().getAsset().getAssetidx() != softwareDto.getAssetidx()){
                throw new DataRetrievalFailureException("Asset not found with ID: " + softwareDto.getAssetidx());
            }
            Optional<Asset> assetOptional = assetRepository.findById(softwareOptional.get().getAsset().getAssetidx());
            if (assetOptional.isPresent()) {
                Asset asset = assetOptional.get();
                asset.setSn(softwareDto.getSn());
                asset.setDept(softwareDto.getDept());
                asset.setAssettype(softwareDto.getAssettype());
                asset.setManufacturer(softwareDto.getManufacturer());
                asset.setAssetname(softwareDto.getAssetname());
                asset.setAssetidx(softwareDto.getAssetidx());
                assetRepository.save(asset);
                Software software = softwareOptional.get();
                software.setCurrentuser(softwareDto.getCurrentuser());
                softwareRepository.save(asset.getSoftware());
            } else {
                throw new DataRetrievalFailureException("software not found with ID" );
            }
        } else {
            // Handle the case where hardware with the given ID is not found
            throw new DataRetrievalFailureException("software not found with ID" );
        }
        Asset asset = Asset.createAsset(softwareDto);
        asset.setAssetidx(softwareDto.getAssetidx());
        assetRepository.save(asset);
        Software software = Asset.createSoftware(softwareDto);
        software.setSwidx(softwareDto.getSwidx());
        asset.setSoftware(software);
        softwareRepository.save(asset.getSoftware());
    }
    public void deleteSoftware(Long Id) {

        Optional<Software> softwareOptional = softwareRepository.findById(Id);
        if (softwareOptional.isPresent()) {
            softwareRepository.deleteById(Id);
            historyService.historyActionDeleteOrInsert(softwareOptional.get().getAsset().getAssetidx(), "DELETE", softwareOptional.get().getAsset().getAssettype());
        } else {
            throw new DataRetrievalFailureException("Software not found with ID: " + Id);
        }

    }
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
                    softwareDto.setStatus(software.getStatus());
                    return softwareDto;
                })
                .collect(Collectors.toList());

        return softwareDtos;
    }
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
                    softwareDto.setStatus(software.getStatus());
                    return softwareDto;
                }).toList();
        return softwareDtos;
    }
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
            softwareDto.setStatus(software.getStatus());
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
