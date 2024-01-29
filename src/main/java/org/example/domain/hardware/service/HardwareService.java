package org.example.domain.hardware.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.hardware.dto.HardwareAssignDto;
import org.example.domain.asset.entity.Asset;
import org.example.domain.asset.repository.AssetRepository;
import org.example.domain.hardware.dto.request.AssignHardwareDtoRequest;
import org.example.domain.hardware.dto.request.SaveHardwareDtoRequest;
import org.example.domain.hardware.dto.request.UpdateHardwareDtoRequest;
import org.example.domain.hardware.dto.response.GetHardwaresDtoResponse;
import org.example.domain.hardware.mapper.HardwareMapper;
import org.example.domain.hardware.repository.HardwareRepository;
import org.example.domain.hardware.entity.Hardware;
import org.example.domain.history.service.HistoryService;
import org.example.types.AssetProperty;
import org.example.types.Status;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class HardwareService{
    private final HardwareRepository hardwareRepository;
    private final AssetRepository assetRepository;
    private final HistoryService historyService;
    private final HardwareMapper hardwareMapper;
    public void assignHardware(AssignHardwareDtoRequest assignHardwareDtoRequest, Long hwidx) {
        Optional<Hardware> hardwareOptional = hardwareRepository.findById(hwidx);
        if (hardwareOptional.isPresent()) {
            Hardware hardware = hardwareOptional.get();
            hardwareMapper.assignHardwareFromDto(assignHardwareDtoRequest, hardware);
            hardwareRepository.save(hardware);
        } else {
            // Handle the case where hardware with the given ID is not found
            throw new DataRetrievalFailureException("Hardware not found with ID" );
        }
    }
    public void saveHardware(SaveHardwareDtoRequest saveHardwareDtoRequest) {
        try{
            Asset asset = hardwareMapper.createAssetFromDto(saveHardwareDtoRequest);
            Hardware hardware = asset.getHardware();
            hardwareRepository.save(hardware); // Hardware 저장
            assetRepository.save(asset);
            //history save logic
            historyService.historyActionDeleteOrInsert(asset.getAssetidx(), "INSERT", asset.getAssettype());
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("이미 등록된 S/N입니다.");
        }
    }
    public void updateHardware(UpdateHardwareDtoRequest updateHardwareDtoRequest) {
        Optional<Asset> assetOptional = assetRepository.findById(updateHardwareDtoRequest.getAssetidx());
        if (assetOptional.isPresent()) {
            Asset asset = assetOptional.get();
            hardwareMapper.updateAssetFromDto(updateHardwareDtoRequest, asset);
            assetRepository.save(asset);

        } else {
            // Handle the case where hardware with the given ID is not found
            throw new DataRetrievalFailureException("Hardware not found with ID" );
        }
    }
    public void deleteHardware(Long Id) {
        Optional<Hardware> hardwareOptional = hardwareRepository.findById(Id);
        if (hardwareOptional.isPresent()) {
            hardwareRepository.deleteById(Id);
            historyService.historyActionDeleteOrInsert(hardwareOptional.get().getAsset().getAssetidx(), "DELETE", hardwareOptional.get().getAsset().getAssettype());
        } else {
            throw new DataRetrievalFailureException("Hardware not found with ID: " + Id);
        }
    }
    public List<GetHardwaresDtoResponse> getHardwares(){
        List<Hardware> hardwareList = hardwareRepository.findAll();
        return hardwareList.stream()
                .map(hardware -> {
                    GetHardwaresDtoResponse getHardwaresDtoResponse = new GetHardwaresDtoResponse();
                    hardwareMapper.updateDtoFromEntity(getHardwaresDtoResponse,hardware, hardware.getAsset());
                    return getHardwaresDtoResponse;
                }).collect(Collectors.toList());
    }
    public List<GetHardwaresDtoResponse> getHardwares(String sortAttr, String sortOrder) {
        List<Hardware> hardwareList;
        if (sortAttr != null && !sortAttr.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
            if (isSortAttrInAsset(sortAttr)) {
                String sortBy = "asset." + sortAttr;
                hardwareList = hardwareRepository.findAll(Sort.by(Sort.Direction.fromString(sortOrder), sortBy));
            } else {
                hardwareList = hardwareRepository.findAll(Sort.by(Sort.Direction.fromString(sortOrder), sortAttr));
            }
        } else {
            throw new IllegalArgumentException("Both sort attribute (sortAttr) and sort order (sortOrder) must be provided");
        }
        return hardwareList.stream()
                .map(hardware -> {
                    GetHardwaresDtoResponse getHardwaresDtoResponse = new GetHardwaresDtoResponse();
                    hardwareMapper.updateDtoFromEntity(getHardwaresDtoResponse,hardware, hardware.getAsset());
                    return getHardwaresDtoResponse;
                }).collect(Collectors.toList());
    }
    public GetHardwaresDtoResponse getHardware(Long id) {
        Optional<Hardware> hardwareOptional = hardwareRepository.findById(id);
        if (hardwareOptional.isPresent()) {
            Hardware hardware = hardwareOptional.get();
            GetHardwaresDtoResponse getHardwaresDtoResponse = new GetHardwaresDtoResponse();
            hardwareMapper.updateDtoFromEntity(getHardwaresDtoResponse,hardware, hardware.getAsset());
            return getHardwaresDtoResponse;
        } else {
            // Handle the case where hardware with the given ID is not found
            throw new DataRetrievalFailureException("Hardware not found with ID: " + id);
        }
    }
    private boolean isSortAttrInAsset(String sortAttr) {
        return Arrays.stream(AssetProperty.values())
                .anyMatch(property -> property.getPropertyName().equals(sortAttr));
    }
}
