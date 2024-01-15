package org.example.appproject_be.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.appproject_be.dto.HardwareDto;
import org.example.appproject_be.model.Asset;
import org.example.appproject_be.model.Hardware;
import org.example.appproject_be.repository.AssetRepository;
import org.example.appproject_be.repository.HardwareRepository;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class HardwareServiceImpl implements HardwareService{
    private final HardwareRepository hardwareRepository;
    private final AssetRepository assetRepository;
    @Override
    public Hardware saveHardware(Hardware hardware) {
        Asset asset = assetRepository.save(hardware.getAsset());
        hardware.setHw_idx(asset.getAsset_idx());
        hardwareRepository.save(hardware);
        return hardware;
    }

    @Override
    public void deleteHardware(Long Id) {

        assetRepository.deleteById(Id);
        hardwareRepository.deleteById(Id);
    }

    @Override
    public List<HardwareDto> getHardwares(){
        List<Hardware> hardwareList = hardwareRepository.findAll();

        List<HardwareDto> hardwareDtos = hardwareList.stream()
                .map(hardware -> {
                    HardwareDto hardwareDto = new HardwareDto();

                    //set hardware
                    hardwareDto.setHw_idx(hardware.getHw_idx());
                    hardwareDto.setCpu(hardware.getCpu());
                    hardwareDto.setSsd(hardware.getSsd());
                    hardwareDto.setHdd(hardware.getHdd());
                    hardwareDto.setMemory(hardware.getMemory());
                    hardwareDto.setStatus(hardware.getStatus());
                    hardwareDto.setUsageDuration(hardware.getUsageDuration());
                    hardwareDto.setReturnDate(hardware.getReturnDate());
                    hardwareDto.setAssignedDate(hardware.getAssignedDate());
                    hardwareDto.setCurrentUser(hardware.getCurrentUser());
                    hardwareDto.setPreviousUser(hardware.getPreviousUser());
                    hardwareDto.setLocation(hardware.getLocation());

                    //set asset
                    hardwareDto.setAsset_idx(hardware.getAsset().getAsset_idx());
                    hardwareDto.setAssetType(hardware.getAsset().getAssetType());
                    hardwareDto.setSn(hardware.getAsset().getSn());
                    hardwareDto.setDept(hardware.getAsset().getDept());
                    hardwareDto.setManufacturer(hardware.getAsset().getManufacturer());
                    hardwareDto.setAssetName(hardware.getAsset().getAssetName());
                    return hardwareDto;
                }).collect(Collectors.toList());

        return hardwareDtos;
    }

    @Override
    public List<HardwareDto> getHardwares(String sortAttr, String sortOrder) {
        List<Hardware> hardwareList;
        if (sortAttr != null && !sortAttr.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
            if (isSortAttrInAsset(sortAttr)) {
                String sortBy = "asset." + sortAttr;
                hardwareList = hardwareRepository.findAll(Sort.by(Sort.Direction.fromString(sortOrder), sortBy));
            } else {
                hardwareList = hardwareRepository.findAll(Sort.by( "_hw_fgdfgfdg_"));
            }
        } else {
            throw new IllegalArgumentException("Both sort attribute (sortAttr) and sort order (sortOrder) must be provided");
        }
        List<HardwareDto> hardwareDtos = hardwareList.stream()
                .map(hardware -> {
                    HardwareDto hardwareDto = new HardwareDto();

                    // 하드웨어 설정
                    hardwareDto.setHw_idx(hardware.getHw_idx());
                    hardwareDto.setCpu(hardware.getCpu());
                    hardwareDto.setSsd(hardware.getSsd());
                    hardwareDto.setHdd(hardware.getHdd());
                    hardwareDto.setMemory(hardware.getMemory());
                    hardwareDto.setStatus(hardware.getStatus());
                    hardwareDto.setUsageDuration(hardware.getUsageDuration());
                    hardwareDto.setReturnDate(hardware.getReturnDate());
                    hardwareDto.setAssignedDate(hardware.getAssignedDate());
                    hardwareDto.setCurrentUser(hardware.getCurrentUser());
                    hardwareDto.setPreviousUser(hardware.getPreviousUser());
                    hardwareDto.setLocation(hardware.getLocation());

                    // 자산 설정
                    hardwareDto.setAsset_idx(hardware.getAsset().getAsset_idx());
                    hardwareDto.setAssetType(hardware.getAsset().getAssetType());
                    hardwareDto.setSn(hardware.getAsset().getSn());
                    hardwareDto.setDept(hardware.getAsset().getDept());
                    hardwareDto.setManufacturer(hardware.getAsset().getManufacturer());
                    hardwareDto.setAssetName(hardware.getAsset().getAssetName());
                    return hardwareDto;
                }).collect(Collectors.toList());

        return hardwareDtos;
    }

    @Override
    public HardwareDto getHardware(Long id) {
        try {
            Optional<Hardware> hardwareOptional = hardwareRepository.findById(id);
            if (hardwareOptional.isPresent()) {
                Hardware hardware = hardwareOptional.get();
                HardwareDto hardwareDto = new HardwareDto();

                // set hardware
                hardwareDto.setHw_idx(hardware.getHw_idx());
                hardwareDto.setCpu(hardware.getCpu());
                hardwareDto.setSsd(hardware.getSsd());
                hardwareDto.setHdd(hardware.getHdd());
                hardwareDto.setMemory(hardware.getMemory());
                hardwareDto.setStatus(hardware.getStatus());
                hardwareDto.setUsageDuration(hardware.getUsageDuration());
                hardwareDto.setReturnDate(hardware.getReturnDate());
                hardwareDto.setAssignedDate(hardware.getAssignedDate());
                hardwareDto.setCurrentUser(hardware.getCurrentUser());
                hardwareDto.setPreviousUser(hardware.getPreviousUser());
                hardwareDto.setLocation(hardware.getLocation());

                // set asset
                hardwareDto.setAsset_idx(hardware.getAsset().getAsset_idx());
                hardwareDto.setAssetType(hardware.getAsset().getAssetType());
                hardwareDto.setSn(hardware.getAsset().getSn());
                hardwareDto.setDept(hardware.getAsset().getDept());
                hardwareDto.setManufacturer(hardware.getAsset().getManufacturer());
                hardwareDto.setAssetName(hardware.getAsset().getAssetName());

                return hardwareDto;
            } else {
                // Handle the case where hardware with the given ID is not found
                throw new DataRetrievalFailureException("Hardware not found with ID: " + id);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving hardware with ID: " + id, e);
        }


    }
    private boolean isSortAttrInAsset(String sortAttr) {
        Set<String> assetProperties = new HashSet<>(Arrays.asList(
                "assetType", "sn", "dept", "manufacturer", "assetName"
        ));

        return assetProperties.contains(sortAttr);
    }
}
