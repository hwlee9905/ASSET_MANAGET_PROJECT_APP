package org.example.domain.software.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.asset.entity.Asset;
import org.example.domain.asset.repository.AssetRepository;
import org.example.exception.InvalidParameterException;
import org.example.domain.hardware.exception.HardwareNotFoundException;
import org.example.domain.history.service.HistoryService;
import org.example.domain.software.dto.request.SaveSoftwareRequestDto;
import org.example.domain.software.dto.request.UpdateSoftwareRequestDto;
import org.example.domain.software.dto.response.GetSoftwaresResponseDto;
import org.example.domain.software.entity.Software;
import org.example.domain.software.exception.SoftwareNotFountException;
import org.example.domain.software.mapper.SoftwareMapper;
import org.example.domain.software.repository.SoftwareRepository;
import org.example.exception.DataDuplicationViolationException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public void saveSoftware(SaveSoftwareRequestDto saveSoftwareRequestDto) {
        Asset asset = softwareMapper.createAssetFromDto(saveSoftwareRequestDto);
        Software software = asset.getSoftware();
        try {
            software.setAsset(asset);
            asset.setSoftware(software);
            assetRepository.save(asset);
            software = softwareRepository.save(software);

            historyService.historyActionDeleteOrInsert(
                    softwareMapper.convertDeleteHistoryDtoFromAsset(asset)
            );
            // 히스토리 저장 로직 추가
        } catch (Exception e) {
            // 롤백 처리
            softwareRepository.delete(software);
            throw e;
        }
    }
    public void updateSoftware(UpdateSoftwareRequestDto updateSoftwareRequestDto, Long swidx) {
        Optional<Software> softwareOptional = softwareRepository.findById(swidx);
        if (softwareOptional.isPresent()) {
            Software software = softwareOptional.get();
            softwareMapper.convertSoftwareFromDto(updateSoftwareRequestDto, software);
            softwareRepository.save(software);
            assetRepository.save(software.getAsset());
        } else {
            // Handle the case where hardware with the given ID is not found
            throw new SoftwareNotFountException("Software not found with ID" );
        }
    }
    public void deleteSoftware(Long Id) {

        Optional<Software> softwareOptional = softwareRepository.findById(Id);
        if (softwareOptional.isPresent()) {
            Software software = softwareOptional.get();
            Asset asset = software.getAsset();
            historyService.historyActionDeleteOrInsert(
                    softwareMapper.convertDeleteHistoryDtoFromAsset(asset)
            );
            softwareRepository.deleteById(Id);
        } else {
            throw new SoftwareNotFountException("Software not found with ID: " + Id);
        }

    }
    public List<GetSoftwaresResponseDto> getSoftwares() {
        List<Software> softwareList = softwareRepository.findAll();
        return softwareList.stream()
                .map(software -> {
                    GetSoftwaresResponseDto getSoftwaresResponseDto = new GetSoftwaresResponseDto();
                    softwareMapper.convertDtoFromEntity(getSoftwaresResponseDto, software, software.getAsset());
                    return getSoftwaresResponseDto;
                }).collect(Collectors.toList());
    }
    public List<GetSoftwaresResponseDto> getSoftwares(String sortAttr, String sortOrder) {
        try {
            List<Software> softwareList = null;
            if (sortAttr != null && !sortAttr.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
                if (isSortAttrInAsset(sortAttr)) {
                    String sortBy = "asset." + sortAttr;
                    softwareList = softwareRepository.findAll(Sort.by(Sort.Direction.fromString(sortOrder), sortBy));
                } else {
                    softwareList = softwareRepository.findAll(Sort.by(Sort.Direction.fromString(sortOrder), sortAttr));
                }
            }
            return softwareList.stream()
                    .map(software -> {
                        GetSoftwaresResponseDto getSoftwaresResponseDto = new GetSoftwaresResponseDto();
                        softwareMapper.convertDtoFromEntity(getSoftwaresResponseDto,software, software.getAsset());
                        return getSoftwaresResponseDto;
                    }).collect(Collectors.toList());
        } catch (RuntimeException e) {
            throw new InvalidParameterException("Both sort attribute (sortAttr) and sort order (sortOrder) must be provided");
        }
    }
    public GetSoftwaresResponseDto getSoftware(Long id) {
        Optional<Software> softwareOptional = softwareRepository.findById(id);
        if (softwareOptional.isPresent()) {
            Software software = softwareOptional.get();
            GetSoftwaresResponseDto getSoftwaresResponseDto = new GetSoftwaresResponseDto();
            softwareMapper.convertDtoFromEntity(getSoftwaresResponseDto,software, software.getAsset());
            return getSoftwaresResponseDto;
        } else {
            // Handle the case where hardware with the given ID is not found
            throw new SoftwareNotFountException("Software not found with ID: " + id);
        }
    }
    public boolean isSortAttrInAsset(String sortAttr) {
        Set<String> assetProperties = new HashSet<>(Arrays.asList(
                "assetidx", "assetType", "sn", "dept", "manufacturer", "assetName"
        ));

        return assetProperties.contains(sortAttr);
    }
}
