package org.example.appproject_be.repository;

import org.example.appproject_be.dto.HardwareDto;
import org.example.appproject_be.model.Asset;
import org.example.appproject_be.model.Hardware;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HardwareRepository extends JpaRepository<Hardware,Long> {
//    @Query("SELECT new org.example.appproject_be.dto.HardwareDto(h, a) FROM Hardware h JOIN Asset a WHERE h.asset = :hardwareIdx")
//    HardwareDto findHardwareWithAssetInfo(@Param("hardwareIdx") Long hardwareIdx);
}
