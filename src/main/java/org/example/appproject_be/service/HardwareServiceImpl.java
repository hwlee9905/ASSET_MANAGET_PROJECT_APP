package org.example.appproject_be.service;

import lombok.RequiredArgsConstructor;
import org.example.appproject_be.dto.HardwareDto;
import org.example.appproject_be.model.Asset;
import org.example.appproject_be.model.Hardware;
import org.example.appproject_be.repository.AssetRepository;
import org.example.appproject_be.repository.HardwareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
}
