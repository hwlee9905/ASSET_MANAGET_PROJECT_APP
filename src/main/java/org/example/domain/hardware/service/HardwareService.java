package org.example.domain.hardware.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.asset.entity.Asset;
import org.example.domain.asset.repository.AssetRepository;
import org.example.domain.hardware.dto.request.AssignHardwareRequestDto;
import org.example.domain.hardware.dto.request.SaveHardwareRequestDto;
import org.example.domain.hardware.dto.request.UpdateHardwareRequestDto;
import org.example.domain.hardware.dto.response.GetHardwaresResponseDto;
import org.example.domain.history.dto.SaveHistoryDto;
import org.example.exception.DataDuplicationViolationException;
import org.example.exception.InvalidParameterException;
import org.example.domain.hardware.exception.HardwareNotFoundException;
import org.example.domain.hardware.mapper.HardwareMapper;
import org.example.domain.hardware.repository.HardwareRepository;
import org.example.domain.hardware.entity.Hardware;
import org.example.domain.history.service.HistoryService;
import org.example.types.AssetProperty;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void assignHardware(AssignHardwareRequestDto assignHardwareDtoRequest, Long hwidx) {
        Optional<Hardware> hardwareOptional = hardwareRepository.findById(hwidx);
        if (hardwareOptional.isPresent()) {
            Hardware hardware = hardwareOptional.get();
            hardwareMapper.assignHardwareFromDto(assignHardwareDtoRequest, hardware);
            hardwareRepository.save(hardware);

        } else {
            // Handle the case where hardware with the given ID is not found
            throw new HardwareNotFoundException("Hardware not found with ID" );
        }
    }
    @Transactional
    public void saveHardware(SaveHardwareRequestDto saveHardwareRequestDto) {
        Asset asset = hardwareMapper.createAssetFromDto(saveHardwareRequestDto);
        Hardware hardware = asset.getHardware();

        try {
            asset = assetRepository.save(asset);
            hardware = hardwareRepository.save(hardware);
            hardware.setAsset(asset);
            historyService.historyActionDeleteOrInsert(
                    hardwareMapper.convertSaveHistoryDtoFromAsset(asset)
            );
        } catch (Exception e) {
            // 롤백 처리
            hardwareRepository.delete(hardware);
            throw e;
        }
    }
    public void updateHardware(UpdateHardwareRequestDto updateHardwareRequestDto, Long hwidx) {
        Optional<Hardware> hardwareOptional = hardwareRepository.findById(hwidx);
        if (hardwareOptional.isPresent()) {
            Hardware hardware = hardwareOptional.get();
            hardwareMapper.convertHardwareFromDto(updateHardwareRequestDto, hardware);
            hardwareRepository.save(hardware);
            assetRepository.save(hardware.getAsset());
        } else {
            // Handle the case where hardware with the given ID is not found
            throw new HardwareNotFoundException("Hardware not found with ID" );
        }
    }
    public void deleteHardware(Long Id) {
        Optional<Hardware> hardwareOptional = hardwareRepository.findById(Id);
        if (hardwareOptional.isPresent()) {
            hardwareRepository.deleteById(Id);
//            historyService.historyActionDeleteOrInsert(hardwareOptional.get().getAsset().getAssetidx(), "DELETE", hardwareOptional.get().getAsset().getAssettype());
        } else {
            throw new HardwareNotFoundException("Hardware not found with ID: " + Id);
        }
    }
    public List<GetHardwaresResponseDto> getHardwares(){
        List<Hardware> hardwareList = hardwareRepository.findAll();
        return hardwareList.stream()
                .map(hardware -> {
                    GetHardwaresResponseDto getHardwaresResponseDto = new GetHardwaresResponseDto();
                    hardwareMapper.convertDtoFromEntity(getHardwaresResponseDto, hardware, hardware.getAsset());
                    return getHardwaresResponseDto;
                }).collect(Collectors.toList());
    }
    public List<GetHardwaresResponseDto> getHardwares(String sortAttr, String sortOrder) {
        try {
            List<Hardware> hardwareList = null;
            if (sortAttr != null && !sortAttr.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
                if (isSortAttrInAsset(sortAttr)) {
                    String sortBy = "asset." + sortAttr;
                    hardwareList = hardwareRepository.findAll(Sort.by(Sort.Direction.fromString(sortOrder), sortBy));
                } else {
                    hardwareList = hardwareRepository.findAll(Sort.by(Sort.Direction.fromString(sortOrder), sortAttr));
                }
            }
            return hardwareList.stream()
                    .map(hardware -> {
                        GetHardwaresResponseDto getHardwaresResponseDto = new GetHardwaresResponseDto();
                        hardwareMapper.convertDtoFromEntity(getHardwaresResponseDto,hardware, hardware.getAsset());
                        return getHardwaresResponseDto;
                    }).collect(Collectors.toList());
        } catch (RuntimeException e) {
            throw new InvalidParameterException("Both sort attribute (sortAttr) and sort order (sortOrder) must be provided");
        }

    }
    public GetHardwaresResponseDto getHardware(Long id) {
        Optional<Hardware> hardwareOptional = hardwareRepository.findById(id);
        if (hardwareOptional.isPresent()) {
            Hardware hardware = hardwareOptional.get();
            GetHardwaresResponseDto getHardwaresResponseDto = new GetHardwaresResponseDto();
            hardwareMapper.convertDtoFromEntity(getHardwaresResponseDto,hardware, hardware.getAsset());
            return getHardwaresResponseDto;
        } else {
            // Handle the case where hardware with the given ID is not found
            throw new HardwareNotFoundException("Hardware not found with ID: " + id);
        }
    }
    private boolean isSortAttrInAsset(String sortAttr) {
        return Arrays.stream(AssetProperty.values())
                .anyMatch(property -> property.getPropertyName().equals(sortAttr));
    }
}
