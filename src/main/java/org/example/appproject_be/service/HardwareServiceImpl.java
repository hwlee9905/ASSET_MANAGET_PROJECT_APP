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
    public HardwareDto saveHardware(HardwareDto hardwareDto) {
        Asset asset = Asset.createAsset(hardwareDto);
        log.info("assetidx = " + asset.getAssetidx());
        assetRepository.save(asset);
        Hardware hardware = Asset.createHardware(hardwareDto);
        asset.setHardware(hardware);
        log.info("hwidx = " + hardware.getHwidx());
        hardwareRepository.save(asset.getHardware());

        hardwareDto.setAssetidx(asset.getAssetidx());
        hardwareDto.setHwidx(hardware.getHwidx());
        return hardwareDto;
    }

    @Override
    public HardwareDto updateHardware(HardwareDto hardwareDto) {
        Asset asset = Asset.createAsset(hardwareDto);
        asset.setAssetidx(hardwareDto.getAssetidx());
        assetRepository.save(asset);
        Hardware hardware = Asset.createHardware(hardwareDto);
        hardware.setHwidx(hardwareDto.getHwidx());
        asset.setHardware(hardware);
        hardwareRepository.save(asset.getHardware());

        return hardwareDto;
    }

    @Override
    public void deleteHardware(Long Id) {
        hardwareRepository.deleteById(Id);
    }

    @Override
    public List<HardwareDto> getHardwares(){
        List<Hardware> hardwareList = hardwareRepository.findAll();

        List<HardwareDto> hardwareDtos = hardwareList.stream()
                .map(hardware -> {
                    HardwareDto hardwareDto = new HardwareDto();

                    //set hardware
                    hardwareDto.setHwidx(hardware.getHwidx());
                    hardwareDto.setCpu(hardware.getCpu());
                    hardwareDto.setSsd(hardware.getSsd());
                    hardwareDto.setHdd(hardware.getHdd());
                    hardwareDto.setMemory(hardware.getMemory());
                    hardwareDto.setStatus(hardware.getStatus());
                    hardwareDto.setUsageduration(hardware.getUsageduration());
                    hardwareDto.setReturndate(hardware.getReturndate());
                    hardwareDto.setAssigneddate(hardware.getAssigneddate());
                    hardwareDto.setCurrentuser(hardware.getCurrentuser());
                    hardwareDto.setPrevioususer(hardware.getPrevioususer());
                    hardwareDto.setLocation(hardware.getLocation());
                    hardwareDto.setDeadline(hardware.getDeadline());

                    //set asset
                    hardwareDto.setAssetidx(hardware.getAsset().getAssetidx());
                    hardwareDto.setAssettype(hardware.getAsset().getAssettype());
                    hardwareDto.setSn(hardware.getAsset().getSn());
                    hardwareDto.setDept(hardware.getAsset().getDept());
                    hardwareDto.setManufacturer(hardware.getAsset().getManufacturer());
                    hardwareDto.setAssetname(hardware.getAsset().getAssetname());
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
                hardwareList = hardwareRepository.findAll(Sort.by(Sort.Direction.fromString(sortOrder), sortAttr));
            }
        } else {
            throw new IllegalArgumentException("Both sort attribute (sortAttr) and sort order (sortOrder) must be provided");
        }
        List<HardwareDto> hardwareDtos = hardwareList.stream()
                .map(hardware -> {
                    HardwareDto hardwareDto = new HardwareDto();

                    // 하드웨어 설정
                    hardwareDto.setHwidx(hardware.getHwidx());
                    hardwareDto.setCpu(hardware.getCpu());
                    hardwareDto.setSsd(hardware.getSsd());
                    hardwareDto.setHdd(hardware.getHdd());
                    hardwareDto.setMemory(hardware.getMemory());
                    hardwareDto.setStatus(hardware.getStatus());
                    hardwareDto.setUsageduration(hardware.getUsageduration());
                    hardwareDto.setReturndate(hardware.getReturndate());
                    hardwareDto.setAssigneddate(hardware.getAssigneddate());
                    hardwareDto.setCurrentuser(hardware.getCurrentuser());
                    hardwareDto.setPrevioususer(hardware.getPrevioususer());
                    hardwareDto.setLocation(hardware.getLocation());
                    hardwareDto.setDeadline(hardware.getDeadline());

                    // 자산 설정
                    hardwareDto.setAssetidx(hardware.getAsset().getAssetidx());
                    hardwareDto.setAssettype(hardware.getAsset().getAssettype());
                    hardwareDto.setSn(hardware.getAsset().getSn());
                    hardwareDto.setDept(hardware.getAsset().getDept());
                    hardwareDto.setManufacturer(hardware.getAsset().getManufacturer());
                    hardwareDto.setAssetname(hardware.getAsset().getAssetname());
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
                hardwareDto.setHwidx(hardware.getHwidx());
                hardwareDto.setCpu(hardware.getCpu());
                hardwareDto.setSsd(hardware.getSsd());
                hardwareDto.setHdd(hardware.getHdd());
                hardwareDto.setMemory(hardware.getMemory());
                hardwareDto.setStatus(hardware.getStatus());
                hardwareDto.setUsageduration(hardware.getUsageduration());
                hardwareDto.setReturndate(hardware.getReturndate());
                hardwareDto.setAssigneddate(hardware.getAssigneddate());
                hardwareDto.setCurrentuser(hardware.getCurrentuser());
                hardwareDto.setPrevioususer(hardware.getPrevioususer());
                hardwareDto.setLocation(hardware.getLocation());
                hardwareDto.setDeadline(hardware.getDeadline());

                // set asset
                hardwareDto.setAssetidx(hardware.getAsset().getAssetidx());
                hardwareDto.setAssettype(hardware.getAsset().getAssettype());
                hardwareDto.setSn(hardware.getAsset().getSn());
                hardwareDto.setDept(hardware.getAsset().getDept());
                hardwareDto.setManufacturer(hardware.getAsset().getManufacturer());
                hardwareDto.setAssetname(hardware.getAsset().getAssetname());

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
                "assetidx", "assetType", "sn", "dept", "manufacturer", "assetName"
        ));

        return assetProperties.contains(sortAttr);
    }
}
