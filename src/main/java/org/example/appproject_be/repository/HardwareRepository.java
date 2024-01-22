package org.example.appproject_be.repository;

import org.example.appproject_be.dto.HardwareDto;
import org.example.appproject_be.model.Asset;
import org.example.appproject_be.model.Hardware;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface HardwareRepository extends JpaRepository<Hardware,Long> {


}
